#!/usr/bin/env bash
# Apply branch protection to the dev and main branches of this repo.
# Ensures the `dev` branch exists (created from `main` if missing), then
# enforces GitHub branch protection rules on both branches:
#   - require a pull request before merging (reject direct pushes)
#   - enforce rules for administrators (admins cannot bypass)
#   - restrict branch deletion (allow_deletions = false)
#   - block force pushes
#
# Usage:  ./scripts/apply-branch-protection.sh
set -euo pipefail

REPO="minhntt1/network-stats-v2"
REPO_URL="repos/$REPO"

echo "==> Ensuring dev branch exists on $REPO"
if gh api "$REPO_URL/branches/dev" >/dev/null 2>&1; then
  echo "    dev already exists"
else
  main_sha="$(gh api "$REPO_URL/branches/main" --jq '.commit.sha')"
  gh api --method POST "$REPO_URL/git/refs" \
    -f ref="refs/heads/dev" \
    -f sha="$main_sha" >/dev/null
  echo "    created dev from main ($main_sha)"
fi

for branch in main dev; do
  echo "==> Applying branch protection to '$branch'"
  gh api --method PUT "$REPO_URL/branches/$branch/protection" \
    --input - <<'JSON'
{
  "required_status_checks": null,
  "enforce_admins": true,
  "required_pull_request_reviews": {
    "required_approving_review_count": 0,
    "dismiss_stale_reviews": false,
    "require_code_owner_reviews": false
  },
  "restrictions": null,
  "required_linear_history": false,
  "allow_force_pushes": false,
  "allow_deletions": false
}
JSON
done

echo "==> Done. main and dev are protected (PR required, no force pushes, no deletion)."
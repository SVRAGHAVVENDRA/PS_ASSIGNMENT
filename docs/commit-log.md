# Task D2: Commit History Review

## Output of `git log --oneline`

```text
e50fae4 fix(notes): resolve author line conflict
f474574 fix(notes): update author line for Savoira
e6a858b fix(notes): update author line for PJP Batch
4b0e8b1 docs(notes): add NOTES.md
9c828df Merge pull request #1 from SVRAGHAVVENDRA/assessment1
520f095 fix(docs): edit first line of docs
d802f37 feat(profile): add location
7da68be fix(docs): add email
d42ff17 feat(profile): add profile
e88a869 feat(assignment1): Hands-on-1
08e860f first commit
```

## Commit Message Review

- **Commit `08e860f`: `first commit`**
  - **Review**: This message does not follow Conventional Commits format (`type(scope): description`). "first commit" is too generic and does not describe what files were added.
  - **Correction**: `chore(repo): initial commit`

- **Commit `e88a869`: `feat(assignment1): Hands-on-1`**
  - **Review**: It uses the `feat` prefix, but "Hands-on-1" could be more descriptive about what was added.
  - **Correction**: `feat(assignment1): add hands-on 1 assignment files`

- Other commit messages like `fix(notes): resolve author line conflict` and `docs(notes): add NOTES.md` follow Conventional Commits format correctly.

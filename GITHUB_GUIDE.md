# GitHub Workflow Guide - StudentResFinderWebApp

## **Quick Push Workflow**

Follow these simple steps to push your changes to GitHub:

### **Step 1: Check what files changed**
```bash
git status
```

### **Step 2: Add changes to staging area**
```bash
git add .
```
(The `.` adds all changed files. Or add specific files: `git add filename`)

### **Step 3: Commit with a message**
```bash
git commit -m "Description of what you changed"
```

### **Step 4: Push to GitHub**
```bash
git push origin main
```
(or replace `main` with your branch name if using a different branch)

---

## **For First-Time Setup**

If you haven't cloned the repo yet:

```bash
git clone <repository-url>
cd StudentResFinderWebApp
```

---

## **Quick 3-Step Command** (One-liner)

```bash
git add . && git commit -m "Your message here" && git push origin main
```

---

## **Best Practices for Group Work**

### ✅ **DO:**
- **Use descriptive commit messages:**
  ```bash
  git commit -m "Add filter functionality to search servlet"
  ```
  NOT: `git commit -m "updates"`

- **Pull before pushing** (to avoid conflicts):
  ```bash
  git pull origin main
  git add .
  git commit -m "message"
  git push origin main
  ```

- **Commit frequently** with meaningful messages
- **Test your code** before committing

### ❌ **DON'T:**
- Push without committing
- Push without pulling first
- Use vague commit messages like "fix", "update", "changes"
- Commit without testing

---

## **Handling Conflicts**

If you get a conflict error when pulling:

1. **See the conflicted files:**
   ```bash
   git status
   ```

2. **Open the file and look for conflict markers:**
   ```
   <<<<<<< HEAD
   your changes
   =======
   their changes
   >>>>>>>
   ```

3. **Edit to keep what you want, then:**
   ```bash
   git add .
   git commit -m "Resolved merge conflict"
   git push origin main
   ```

4. **OR ask for help if unsure!**

---

## **Useful Commands**

| Command | Purpose |
|---------|---------|
| `git status` | See which files changed |
| `git log` | See commit history |
| `git pull origin main` | Get latest changes from GitHub |
| `git branch` | See all branches |
| `git diff` | See exact changes in files |

---

## **Need Help?**

Ask a team lead or experienced member if you encounter any issues. Don't be afraid to ask!

---

**Last Updated:** May 5, 2026

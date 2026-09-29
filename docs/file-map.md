# Users Feature File Map

## Authentication

### GET /login
Template: `login.html`

Contract:
- Displays the login page
- Login form uses:
  - `username`
  - `password`
- Form submits to POST `/login`

### GET /register
Template: `register.html`

Contract:
- Displays the registration page
- Registration form uses:
  - `username`
  - `password`
  - `confirmPassword`

### POST /register
Template on error: `register.html`

Contract:
- Form fields:
  - `username`
  - `password`
  - `confirmPassword`
- Displays model attribute `error` when registration fails
- Successful registration redirects to `/login?registered`

---

## User Administration

### GET /admin/users
Template: `userAdmin.html`

Contract:
- Model attribute: `users`
- Each user contains:
  - `id`
  - `username`
  - `role`
  - `enabled`

### GET /admin/users/edit/{id}
Template: `editUser.html`

Contract:
- Model attribute: `user`
- Uses:
  - `user.id`
  - `user.username`
  - `user.role`
  - `user.enabled`

### POST /admin/users/edit

Contract:
- Form fields:
  - `id`
  - `role`
  - `enabled`
- Password is not edited

### GET /admin/users/delete/{id}
Template: `confirmDeleteUser.html`

Contract:
- Model attribute: `user`
- Displays user information before deletion

### POST /admin/users/delete

Contract:
- Form field:
  - `id`
- Deletes the selected user
- Redirects to `/admin/users`
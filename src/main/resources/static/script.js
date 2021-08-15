const editModal = document.getElementById('editModal');
const deleteModal = document.getElementById('deleteModal');

editModal.addEventListener('show.bs.modal', function (event) {

    const button = event.relatedTarget;

    const userId = button.getAttribute('data-bs-userId');
    const userName = button.getAttribute('data-bs-userName');
    const userLast = button.getAttribute('data-bs-userLast');
    const userAge = button.getAttribute('data-bs-userAge');
    const userLogin = button.getAttribute('data-bs-userLogin');

    const modalTitle = editModal.querySelector('.modal-title');
    const modalBodyInputId = editModal.querySelector('[data-edit-id]');
    const modalBodyInputName = editModal.querySelector('[data-edit-name]');
    const modalBodyInputLast = editModal.querySelector('[data-edit-last]');
    const modalBodyInputAge = editModal.querySelector('[data-edit-age]');
    const modalBodyInputLogin = editModal.querySelector('[data-edit-login]');

    modalTitle.textContent = 'Edit user'
    modalBodyInputId.value = userId
    modalBodyInputName.value = userName
    modalBodyInputLast.value = userLast
    modalBodyInputAge.value = userAge
    modalBodyInputLogin.value = userLogin
})

deleteModal.addEventListener('show.bs.modal', function (event) {

    const buttonDelete = event.relatedTarget;

    const userId = buttonDelete.getAttribute('data-bs-userId');
    const userName = buttonDelete.getAttribute('data-bs-userName');
    const userLast = buttonDelete.getAttribute('data-bs-userLast');
    const userAge = buttonDelete.getAttribute('data-bs-userAge');
    const userLogin = buttonDelete.getAttribute('data-bs-userLogin');
    const userRole = buttonDelete.getAttribute('data-bs-userRole');

    const deleteTitle = deleteModal.querySelector('[delete-title]');
    const deleteBodyId = deleteModal.querySelector('[data-delete-id]');
    const deleteBodyName = deleteModal.querySelector('[data-delete-name]');
    const deleteBodyLast = deleteModal.querySelector('[data-delete-last]');
    const deleteBodyAge = deleteModal.querySelector('[data-delete-age]');
    const deleteBodyLogin = deleteModal.querySelector('[data-delete-login]');
    const deleteBodyRole = deleteModal.querySelector('[data-delete-role]');

    deleteTitle.textContent = 'Delete user'
    deleteBodyId.value = userId
    deleteBodyName.value = userName
    deleteBodyLast.value = userLast
    deleteBodyAge.value = userAge
    deleteBodyLogin.value = userLogin
    deleteBodyRole.value = userRole
})
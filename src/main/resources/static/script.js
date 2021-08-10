const editModal = document.getElementById('editModal');
editModal.addEventListener('show.mdb.modal', function (event) {

    const button = event.relatedTarget;

    const userId = button.getAttribute('data-mdb-userId');
    const userName = button.getAttribute('data-mdb-userName');
    const userLast = button.getAttribute('data-mdb-userLast');
    const userAge = button.getAttribute('data-mdb-userAge');
    const userLogin = button.getAttribute('data-mdb-userLogin');
    console.log(userId, userName)

    const modalTitle = editModal.querySelector('.modal-title');
    const modalBodyInputId = editModal.querySelector('[data-id]');
    const modalBodyInputName = editModal.querySelector('[data-name]');
    const modalBodyInputLast = editModal.querySelector('[data-last]');
    const modalBodyInputAge = editModal.querySelector('[data-age]');
    const modalBodyInputLogin = editModal.querySelector('[data-login]');

    modalTitle.textContent = 'Edit profile from ' + userLast + ' ' + userName
    modalBodyInputId.value = userId
    modalBodyInputName.value = userName
    modalBodyInputLast.value = userLast
    modalBodyInputAge.value = userAge
    modalBodyInputLogin.value = userLogin
})



    const deleteModal = document.getElementById('deleteModal');
    deleteModal.addEventListener('show.mdb.modal', function (event) {

    const buttonDelete = event.relatedTarget;

    const userId = buttonDelete.getAttribute('data-mdb-userId');
    const userName = buttonDelete.getAttribute('data-mdb-userName');
    const userLast = buttonDelete.getAttribute('data-mdb-userLast');

    const deleteTitle = deleteModal.querySelector('[delete-title]');
    const deleteBodyId = deleteModal.querySelector('[data-id]');
    const deleteBodyName = deleteModal.querySelector('[data-name]');
    const deleteBodyLast = deleteModal.querySelector('[data-last]');

    deleteTitle.textContent = 'Delete user ' + userLast + ' ' + userName + '?'
    deleteBodyId.value = userId
    deleteBodyName.value = userName
    deleteBodyLast.value = userLast
})
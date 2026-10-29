// List page - open delete confirmation modal
function openDeleteModal(transactionId, customerName) {
    document.getElementById('deleteTransactionId').textContent = transactionId;
    document.getElementById('deleteCustomerName').textContent = customerName;
    document.getElementById('deleteForm').action = '/transactions/delete/' + encodeURIComponent(transactionId);

    const modal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
    modal.show();
}

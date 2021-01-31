<div class="container" aria-live="polite" aria-atomic="true" style="position: relative;">
    <!-- Toast succes -->
    <div id="toast-succes" class="toast" data-autohide="false" style="position: absolute; top: 10px; right: 0; z-index: 9999;" >
        <div class="toast-header bg-success text-light">
            <strong class="mr-auto">
                <labe id="title-succes"></labe>
            </strong>

            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="toast-body">
            <labe id="message-succes"></labe>
        </div>
    </div>

    <!-- Toast Alert -->
    <div id="toast-alert" class="toast" data-autohide="false" style="position: absolute; top: 100px; right: 0; z-index: 9999;">
        <div class="toast-header bg-warning text-light">
            <strong class="mr-auto">
                <labe id="title-alert"></labe>
            </strong>

            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="toast-body">
            <labe id="message-alert"></labe>
        </div>
    </div>

    <!-- Toast Danger -->
    <div id="toast-danger" class="toast" data-autohide="false" style="position: absolute; top: 100px; right: 0; z-index: 9999;">
        <div class="toast-header bg-danger text-light">
            <strong class="mr-auto">
                <labe id="title-danger"></labe>
            </strong>

            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="toast-body">
            <labe id="message-danger"></labe>
        </div>
    </div>
</div>
<script type="text/javascript"	src="${context}/locals/js/shared/came.notify.js"></script>
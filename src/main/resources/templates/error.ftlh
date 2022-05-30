<#ftl encoding="utf-8">
<#import "_document.ftlh" as document>
<#import '/spring.ftl' as spring>
<@document.document "main" "Error">
    <div class="px-4 py-5 my-5 text-center">
        <h1 class="display-5 fw-bold">Something went wrong. We're on it already</h1>
        <div class="col-lg-6 mx-auto">
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <a role="button" class="btn btn-primary btn-lg px-4 gap-3" href="<@spring.url '/'/>">Return to the main page</a>
            </div>
        </div>
    </div>
</@document.document>
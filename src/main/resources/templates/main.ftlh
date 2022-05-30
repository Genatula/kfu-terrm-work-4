<#ftl encoding="utf-8">
<#import "_document.ftlh" as document>
<#import '/spring.ftl' as spring>
<@document.document "main" "Community center">
    <div class="px-4 py-5 my-5 text-center">
        <h1 class="display-5 fw-bold">Community center</h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">
                Community center that works for everyone. Our team strives to abolish restrictions, make our place as safe as possible and accept everyone. We bet you'll find something that suits your interests, whether it's just an article or a meeting.
            </p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <a role="button" class="btn btn-primary btn-lg px-4 gap-3" href="<@spring.url '/feed'/>">Start using now</a>
            </div>
        </div>
    </div>
</@document.document>
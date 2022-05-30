<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import  '/spring.ftl' as spring>
<@document.document 'user_edit' 'Edit password'>
    <@document.header 'feed' />
    <div class="col-12 m-3 user-form">
        <h1>Edit password</h1>
        <form action="<@spring.url '/user/edit/password'/>" method="post">
            <div class="form-floating mb-3">
                <@spring.bind 'form.oldPassword' />
                <#if old_password_error??>
                    <input id="oldPassword" type="password" name="oldPassword" class="form-control border-danger">
                    <span class="text-danger error-message">Incorrect password</span>
                <#else >
                    <input id="oldPassword" type="password" name="oldPassword" class="form-control">
                </#if>
                <label for="oldPassword">Enter old password</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.password' />
                <#if spring.status.error>
                    <input id="password" type="password" name="password" class="form-control border-danger">
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else >
                    <input id="password" type="password" name="password" class="form-control">
                </#if>
                <label for="password">Enter new password</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.matchingPassword' />
                <#if spring.status.error>
                    <input id="matchingPassword" type="password" name="matchingPassword" class="form-control border-danger">
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else >
                    <input id="matchingPassword" type="password" name="matchingPassword" class="form-control">
                </#if>
                <label for="matchingPassword">Enter new password again</label>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</@document.document>
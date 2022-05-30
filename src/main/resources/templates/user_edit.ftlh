<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'user_edit' 'Edit profile info'>
    <@document.header 'feed'/>
    <div class="col-12 m-3 user-form">
        <h1>Edit profile info</h1>
        <form action="<@spring.url '/user/edit'/>" method="post">
            <div class="form-floating mb-3">
                <@spring.bind 'form.email' />
                <#if spring.status.error>
                    <input id="email" type="email" name="email" value="${form.email!}" class="form-control border-danger">
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#elseif email_error??>
                    <input id="email" type="email" name="email" value="${form.email!}" class="form-control border-danger">
                    <br>
                    <b class="text-danger error-message">User with such email already exists</b>
                <#else>
                    <input id="email" type="email" name="email" value="${form.email!}" class="form-control">
                </#if>
                <label for="email">Enter email</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.username' />
                <#if spring.status.error>
                    <input id="username" type="text" name="username" value="${form.username!}" class="form-control border-danger">
                    <@spring.showErrors 'br' 'error-message text-danger'/>
                <#elseif username_error??>
                    <input id="username" type="text" name="username" value="${form.username!}" class="form-control border-danger">
                    <br>
                    <b class="text-danger error-message">User with such username already exists</b>
                <#else >
                    <input id="username" type="text" name="username" value="${form.username!}" class="form-control">
                </#if>
                <label for="username">Enter username</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.firstname' />
                <#if spring.status.error>
                    <input id="firstname" type="text" name="firstname" value="${form.firstname!}" class="form-control border-danger">
                    <br>
                    <b class="text-danger error-message">Firstname must not be empty</b>
                <#else >
                    <input id="firstname" type="text" name="firstname" value="${form.firstname!}" class="form-control">
                </#if>
                <label for="firstname">Enter firstname</label>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
        <a href="<@spring.url '/user/edit/password'/>">Update password</a>
    </div>
</@document.document>
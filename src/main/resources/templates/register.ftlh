<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'register' 'Sign up'>
    <main class="form-signin">
        <form method="post" action="<@spring.url '/register'/>">
            <h1 class="h3 mb-3 fw-normal">Register</h1>
            <#if RequestParameters.username_error??>
                <div class="alert alert-danger">
                    User with such username already exists. Want to <a href="<@spring.url '/login'/>">sign in</a>?
                </div>
            <#elseif RequestParameters.email_error??>
                <div class="alert alert-danger">
                    User with such email already exists. Want to <a href="<@spring.url '/login'/>">sign in</a>?
                </div>
            </#if>
            <div class="form-floating mb-3">
                <@spring.bind "form.username"/>
                <#if spring.status.error>
                    <input type="text" class="form-control border-danger" placeholder="Username" value="${form.username!}" name="username" id="username">
                    <span class="text-danger error-message">Username must not be less than 3 characters and more than 50 characters long</span>
                <#else>
                    <input type="text" class="form-control" placeholder="Username" value="${form.username!}" name="username" id="username">
                </#if>
                <label for="username">Username</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind "form.email"/>
                <#if spring.status.error>
                    <input type="email" class="form-control border-danger" placeholder="Email" value="${form.email!}" name="email" id="email">
                <#else>
                    <input type="email" class="form-control" placeholder="Email" value="${form.email!}" name="email" id="email">
                </#if>
                <@spring.showErrors "<br/>" "error-message text-danger"/>
                <label for="email">Email</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind "form.firstname"/>
                <#if spring.status.error>
                    <input type="text" class="form-control border-danger" placeholder="Firstname" value="${form.firstname!}" name="firstname" id="firstname">
                <#else>
                    <input type="text" class="form-control" placeholder="Firstname" value="${form.firstname!}" name="firstname" id="firstname">
                </#if>
                <@spring.showErrors "<br/>" "error-message text-danger"/>
                <label for="firstname">Firstname</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind "form.password"/>
                <#if spring.status.error>
                    <input type="password" class="form-control border-danger" placeholder="Password" name="password" id="password">
                <#else>
                    <input type="password" class="form-control" placeholder="Password" name="password" id="password">
                </#if>
                <@spring.showErrors "<br/>" "error-message text-danger"/>
                <label for="password">Password</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind "form.matchingPassword"/>
                <#if spring.status.error>
                    <input type="password" class="form-control border-danger" placeholder="Enter password again" name="matchingPassword" id="matchingPassword">
                <#else>
                    <input type="password" class="form-control" placeholder="Enter password again" name="matchingPassword" id="matchingPassword">
                </#if>
                <@spring.showErrors "<br/>" "error-message text-danger"/>
                <label for="matchingPassword">Enter password again</label>
            </div>
            <button type="submit" class="w-100 btn btn-lg btn-primary">Sign up</button>
        </form>
    </main>
</@document.document>
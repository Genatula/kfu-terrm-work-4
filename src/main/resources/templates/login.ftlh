<#ftl encoding="utf-8">
<#import "_document.ftlh" as document>
<#import "/spring.ftl" as spring>
<@document.document "login" "Login">
    <main class="form-signin">
        <form method="post" action="<@spring.url '/login'/>" >
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
            <#if RequestParameters.error??>
                <div class="alert alert-danger">
                    Invalid username and/or password
                </div>
            </#if>
            <div class="form-floating mb-3">
                <input id="username" type="text" class="form-control" name="username" placeholder="Username">
                <label for="username">Username</label>
            </div>
            <div class="form-floating mb-3">
                <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                <label for="password">Password</label>
            </div>
            <button type="submit" class="w-100 btn btn-lg btn-primary">Sign in</button>
        </form>
        <div class="d-grid gap-2 mt-4">
            <a href="<@spring.url '/register'/>" role="button" class="btn-outline-primary btn">Sign up</a>
            <a href="<@spring.url '/'/>" role="button" class="btn-outline-danger btn">Main page</a>
        </div>
    </main>
</@document.document>
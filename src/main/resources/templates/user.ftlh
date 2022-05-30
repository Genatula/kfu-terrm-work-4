<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'user' 'Profile'>
    <main class="profile">
        <img src="<@spring.url "/images/${filename}"/>" class="rounded mb-1" width="200" height="200" alt="Profile pic">
        <h2>${user.username}</h2>
        <div class="d-grid gap-2">
            <a role="button" class="btn btn-outline-primary mb-2" href="<@spring.url '/user/edit'/>">Edit your profile</a>
            <a role="button" class="btn btn-outline-primary mb-2" href="<@spring.url '/logout'/>">Logout</a>
            <a role="button" class="btn btn-link" href="<@spring.url '/feed'/>">Feed</a>
        </div>
    </main>
</@document.document>
<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'article' article.caption>
    <@document.header 'articles'/>
    <div class="container">
        <div class="row justify-content-center">
            <#if is_admin>
                <h1 class="col-8">${article.caption}</h1>
                <a role="button" class="btn btn-primary col-2" href="<@spring.url '/articles/${article.id}/edit'/>">Edit article</a>
                <span class="text-muted col-2 text-center pt-2">Created ${article.creationDate?string}</span>
            <#else >
                <h1 class="col-10">${article.caption}</h1>
                <span class="text-muted col-2 text-center pt-2">Created ${article.creationDate?string}</span>
            </#if>
        </div>
        <div class="row">
            <h2 class="text-muted">Author : ${author.firstname}</h2>
        </div>
        <div class="row">
            <p>${article.body}</p>
        </div>
        <div class="row mt-6 ms-1">
            <h2>Comments: </h2>
        </div>
        <form method="post" action="<@spring.url '/articles/${article.id?string}'/>" class="row ms-1 comment-form">
            <div class="form-floating mb-3">
                <@spring.bind 'form.body'/>
                <#if spring.status.error>
                    <textarea id="body" name="body" class="form-control border-danger">${form.body!}</textarea>
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else >
                    <textarea id="body" name="body" class="form-control">${form.body!}</textarea>
                </#if>
                <label for="body" class="ms-1">Enter your comment</label>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Send</button>
            </div>
        </form>
        <#list comments as comment>
            <div class="container border-1 border-info">
                <div class="row mt-3">
                    <h3 class="col-10">${comment.author.firstname} says :</h3>
                    <span class="text-muted col-2 mt-2">${comment.date?string}</span>
                </div>
                <div class="row">
                    <p>${comment.body}</p>
                </div>
            </div>
        </#list>
    </div>
</@document.document>
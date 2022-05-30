<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'article' 'New Article'>
    <@document.header 'articles'/>
    <div class="col-12 m-3 article-form">
        <h1>Create new article</h1>
        <form action="<@spring.url '/articles/create'/>" method="post" enctype="multipart/form-data">
            <div class="form-floating mb-3">
                <@spring.bind 'form.caption'/>
                <#if spring.status.error>
                    <input id="caption" type="text" name="caption" value="${form.caption!}" class="form-control border-danger">
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else>
                    <input id="caption" type="text" name="caption" value="${form.caption!}" class="form-control">
                </#if>
                <label for="caption">Enter caption</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.shortDescription'/>
                <#if spring.status.error>
                    <input id="shortDescription" type="text" name="shortDescription" value="${form.shortDescription!}" class="form-control border-danger">
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else>
                    <input id="shortDescription" type="text" name="shortDescription" value="${form.shortDescription!}" class="form-control">
                </#if>
                <label for="shortDescription">Enter short description</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.body'/>
                <#if spring.status.error>
                    <textarea id="body" type="" name="body" class="form-control border-danger">${form.body!}</textarea>
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else>
                    <textarea id="body" type="" name="body" class="form-control">${form.body!}</textarea>
                </#if>
                <label for="body">Enter body</label>
            </div>
            <div class="input-group mb-3">
                <@spring.bind 'form.file'/>
                <input id="file" type="file" name="file" value="${form.file!}" class="form-control">
                <#if incorrect_extension??>
                    <br>
                    <b class="error-message text-danger">Invalid file extension</b>
                <#elseif empty_file??>
                    <br>
                    <b class="error-message text-danger">An image for the article must be provided</b>
                </#if>
                <label for="file" class="input-group-text">Upload image</label>
            </div>
            <div class="container">
                <h3 class="ms-3">Select tags: </h3>
                <#list tags as tag>
                    <div class="form-check form-check-inline ms-3">
                        <input class="form-check-input" type="checkbox" id="${tag.name}" value="${tag.name}" name="tags">
                        <label class="form-check-label" for="${tag.name}">${tag.name}</label>
                    </div>
                </#list>
            </div>
            <br>
            <button type="submit" class="btn btn-primary mt-3">Create</button>
        </form>
    </div>
</@document.document>
<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'article' 'Edit Article'>
    <@document.header 'articles'/>
    <div class="col-12 m-3 article-form">
        <h1>Update article image</h1>
        <form action="<@spring.url '/articles/${article.id}/edit/image'/>" method="post" enctype="multipart/form-data">
            <div class="input-group mb-3">
                <@spring.bind 'form.file'/>
                <input id="file" type="file" name="file" class="form-control">
                <#if incorrect_extension??>
                    <br>
                    <b class="error-message text-danger">Invalid file extension</b>
                <#elseif empty_file??>
                    <br>
                    <b class="error-message text-danger">An image for the article must be provided</b>
                </#if>
                <label for="file" class="input-group-text">Upload image</label>
            </div>
            <br>
            <button type="submit" class="btn btn-primary mt-3">Save</button>
        </form>
    </div>
</@document.document>
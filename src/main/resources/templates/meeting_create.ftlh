<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'meetings' 'New Meeting'>
    <@document.header 'meetings'/>
    <div class="col-12 m-3 meeting-form">
        <h1>Create new meeting</h1>
        <form action="<@spring.url '/meetings/create'/>" method="post" enctype="multipart/form-data">
            <div class="form-floating mb-3">
                <@spring.bind 'form.name'/>
                <#if spring.status.error>
                    <input id="name" type="text" name="name" value="${form.name!}" class="form-control border-danger">
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else>
                    <input id="name" type="text" name="name" value="${form.name!}" class="form-control">
                </#if>
                <label for="name">Enter caption</label>
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
                <@spring.bind 'form.description'/>
                <#if spring.status.error>
                    <textarea id="description" type="" name="description" class="form-control border-danger">${form.description!}</textarea>
                    <@spring.showErrors '<br>' 'error-message text-danger'/>
                <#else>
                    <textarea id="description" type="" name="description" class="form-control">${form.description!}</textarea>
                </#if>
                <label for="description">Enter body</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.location'/>
                <#if spring.status.error>
                    <input id="location" type="text" name="location" class="form-control border-danger" value="${form.location!}">
                    <span class="error-message text-danger">Location must be provided</span>
                <#else>
                    <input id="location" type="text" name="location" class="form-control" value="${form.location!}">
                </#if>
                <label for="location">Enter location</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.date'/>
                <#if spring.status.error>
                    <input id="date" type="date" name="date" class="form-control border-danger" value="${form.date!}">
                    <span class="error-message text-danger">Date must be provided</span>
                <#else>
                    <input id="date" type="date" name="date" class="form-control" value="${form.date!}">
                </#if>
                <label for="date">Enter date</label>
            </div>
            <div class="form-floating mb-3">
                <@spring.bind 'form.time'/>
                <#if spring.status.error>
                    <input id="time" type="time" name="time" class="form-control border-danger" value="${form.time!}">
                    <span class="error-message text-danger">Time must be provided</span>
                <#else>
                    <input id="time" type="time" name="time" class="form-control" value="${form.time!}">
                </#if>
                <label for="time">Enter time</label>
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
            <br>
            <button type="submit" class="btn btn-primary mt-3">Create</button>
        </form>
    </div>
</@document.document>
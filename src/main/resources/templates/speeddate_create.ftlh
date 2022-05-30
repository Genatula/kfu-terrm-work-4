<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'speeddate' 'New Speed Dates'>
    <@document.header 'speeddates'/>
    <div class="col-12 m-3 speeddate-form">
        <h1>Create new speed date</h1>
        <form action="<@spring.url '/speeddates/create'/>" method="post" enctype="multipart/form-data">
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
            <div class="container mb-3">
                <@spring.bind 'form.target'/>
                <h3 class="ms-3">Select target: </h3>
                <div class="form-check form-check-inline ms-3">
                    <input class="form-check-input" type="radio" id="partnership" value="partnership" name="target">
                    <label class="form-check-label" for="partnership">Partnership</label>
                </div>
                <div class="form-check form-check-inline ms-3">
                    <input class="form-check-input" type="radio" id="friendship" value="friendship" name="target">
                    <label class="form-check-label" for="friendship">Friendship</label>
                </div>
                <#if spring.status.error>
                    <span class="error-message text-danger">Target audience for the speed date must be selected</span>
                </#if>
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
<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'meetings' meeting.caption>
    <@document.header 'meetings'  />
    <div class="container">
        <div class="row justify-content-center">
            <#if is_admin>
                <h1 class="col-8">${meeting.caption}</h1>
                <a role="button" class="btn btn-primary col-2" href="<@spring.url '/meetings/${meeting.id}/edit'/>">Edit meeting</a>
                <span class="text-muted col-2 text-center pt-2">Created ${meeting.creationDate?string}</span>
            <#else >
                <h1 class="col-10">${meeting.caption}</h1>
                <span class="text-muted col-2 text-center pt-2">Created ${meeting.creationDate?string}</span>
            </#if>
        </div>
        <div class="row">
            <h2 class="text-dark">Date : ${meeting.date?string} at ${meeting.time?string["HH:mm"]}</h2>
        </div>
        <div class="row">
            <h2 class="text-dark">Location : ${meeting.location}</h2>
        </div>
        <div class="row">
            <p>${meeting.description}</p>
        </div>
        <input type="hidden" id="id" name="id" value="${meeting.id}">
        <div class="row align-content-center meeting-btn">
            <#if is_participant >
                <button type="button" class="btn btn-primary" id="participate-btn" hidden>Take part</button>
                <button type="button" class="btn btn-danger" id="undo-participate-btn">Leave the meeting</button>
            <#else >
                <button type="button" class="btn btn-primary" id="participate-btn">Take part</button>
                <button type="button" class="btn btn-danger" id="undo-participate-btn" hidden>Leave the meeting</button>
            </#if>
        </div>
    </div>
</@document.document>
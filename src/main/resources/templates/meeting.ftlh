<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'meetings' meeting.caption>
    <@document.header 'meetings'  />
    <div class="container">
        <div class="row justify-content-center">
            <h1 class="col-10">${meeting.caption}</h1>
            <span class="text-muted col-2 text-center pt-2">Created ${meeting.creationDate?string}</span>
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
        <div class="row align-content-center">
            <button type="button" class="btn btn-primary" id="participate-btn">Take part</button>
        </div>
    </div>
</@document.document>
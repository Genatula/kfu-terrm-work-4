<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'speeddates' speeddate.caption>
    <@document.header 'speeddates'  />
    <div class="container">
        <div class="row justify-content-center">
            <h1 class="col-10">${speeddate.caption}</h1>
            <span class="text-muted col-2 text-center pt-2">Created ${speeddate.creationDate?string}</span>
        </div>
        <div class="row">
            <h2 class="text-dark">Date : ${speeddate.date?string} at ${speeddate.time?string["HH:mm"]}</h2>
        </div>
        <div class="row">
            <h2 class="text-dark">Location : ${speeddate.location}</h2>
        </div>
        <div class="row">
            <h2 class="${speeddate.target?string}-header">Target : ${speeddate.target?string}</h2>
        </div>
        <div class="row">
            <p>${speeddate.description}</p>
        </div>
        <div class="row align-content-center">
            <button type="button" class="btn btn-primary" id="participate-btn">Take part</button>
        </div>
    </div>
</@document.document>
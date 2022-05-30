<#ftl encoding='utf-8'>
<#import '_document.ftlh' as document>
<#import '/spring.ftl' as spring>
<@document.document 'meetings' 'Meetings'>
    <@document.header 'meetings'/>
    <#if is_admin>
        <div class="row">
            <div class="col-4"></div>
            <a role="button" class="btn btn-primary col-4" href="<@spring.url '/meetings/create'/>">Create meeting</a>
            <div class="col-4"></div>
        </div>
    </#if>
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <#list meetings as meeting>
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="<@spring.url '/images/' + meeting.getImage().getFilename() + meeting.getImage().getExtension()/>" class="bd-placeholder-img card-img-top" height="225" width="100%">
                        <div class="card-body">
                            <h3 class="card-header">${meeting.getCaption()}</h3>
                            <p class="card-text">${meeting.getShortDescription()}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <a role="button" class="btn btn-sm btn-outline-secondary" href="<@spring.url "/" + meeting.getUrlPath() + "/" + meeting.getId()/>">Читать</a>
                                <small class="text-muted">${meeting.getCreationDate()}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@document.document>
<#ftl encoding='utf-8'>
<#import "_document.ftlh" as document/>
<#import "/spring.ftl" as spring/>
<@document.document "feed" "Feed">
    <@document.header "feed"/>
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <#list posts as post>
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="<@spring.url '/images/' + post.getImage().getFilename() + post.getImage().getExtension()/>" class="bd-placeholder-img card-img-top" height="225" width="100%">
                        <div class="card-body">
                            <h3 class="card-header">${post.getCaption()}</h3>
                            <p class="card-text">${post.getShortDescription()}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <a role="button" class="btn btn-sm btn-outline-secondary" href="<@spring.url "/" + post.getUrlPath() + "/" + post.getId()/>">Read</a>
                                <small class="text-muted">${post.getCreationDate()}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@document.document>
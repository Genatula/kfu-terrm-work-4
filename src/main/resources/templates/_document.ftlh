<#macro document static_name title>
    <#import "/spring.ftl" as spring>
    <!DOCTYPE html>
    <html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<@spring.url '/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<@spring.url "/css/" + static_name + ".css"></@spring.url>">
    <link rel="stylesheet" href="<@spring.url "/css/generic.css"></@spring.url>">
    <script src="<@spring.url "/js/bootstrap.bundle.min.js"></@spring.url>"></script>
    <script src="<@spring.url "/js/" + static_name + ".js"></@spring.url>"></script>
    <script src="<@spring.url "/js/generic.js"></@spring.url>"></script>
    <title>${title}</title>
</head>
<body>
<#nested>
</body>
</html>
</#macro>

<#macro header actibeTab>
    <#import "/spring.ftl" as spring/>
    <header class="p-3 mb-3 border-bottom">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none" href="<@spring.url '/'/>">
                    Logo
                </a>
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li>
                    <#if actibeTab == "feed">
                        <a class="nav-link px-2 link-secondary" href="<@spring.url '/feed'/>">Feed</a>
                    <#else>
                        <a class="nav-link px-2 link-dark" href="<@spring.url '/feed'/>">Feed</a>
                    </#if>
                    </li>
                    <li>
                        <#if actibeTab == "articles">
                            <a class="nav-link px-2 link-secondary" href="<@spring.url '/articles'/>">Articles</a>
                        <#else>
                            <a class="nav-link px-2 link-dark" href="<@spring.url '/articles'/>">Articles</a>
                        </#if>
                    </li>
                    <li>
                        <#if actibeTab == "meetings">
                            <a class="nav-link px-2 link-secondary" href="<@spring.url '/meetings'/>">Meetings</a>
                        <#else>
                            <a class="nav-link px-2 link-dark" href="<@spring.url '/meetings'/>">Meetings</a>
                        </#if>
                    </li>
                    <li>
                        <#if actibeTab == "speeddates">
                            <a class="nav-link px-2 link-secondary" href="<@spring.url '/speeddates'/>">Speed dates</a>
                        <#else>
                            <a class="nav-link px-2 link-dark" href="<@spring.url '/speeddates'/>">Speed dates</a>
                        </#if>
                    </li>
                </ul>
                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                    <input class="form-control" type="search" placeholder="Search" name="search" id="search">
                </form>
                <div class="dropdown text-end">
                    <a id="dropdownUser1" class="d-block link-dark text-decoration-none dropdown-toggle" href="<@spring.url '/user'/>" data-bs-toggle="dropdown" aria-expanded="false">
                        Menu
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                        <li>
                            <a href="<@spring.url '/user'/>" class="dropdown-item">Profile</a>
                        </li>
                        <li>
                            <a href="<@spring.url '/logout'/>" class="dropdown-item">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
</#macro>

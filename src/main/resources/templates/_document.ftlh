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

<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
Add new user page
${message?ifExists}
<@l.login "/registration"/>
</@c.page>
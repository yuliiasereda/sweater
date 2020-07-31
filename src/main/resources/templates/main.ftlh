<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>
  <@l.logout/>
</div>

<form method="POST">
  <input type="text" name="text" placeholder="Enter your message">
  <input type="text" name="tag" placeholder="Enter your tag">
  <input type="hidden" name="_csrf" value="${_csrf.token}"/>
  <button type="submit">Add</button>
</form>

<div>List of messages: </div>

<form method="GET" action="/main">
  <input type="text" name="filter" value="${filter}">
  <button type="submit">Search</button>
</form>

<#list messages as message>
  <div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
  </div>
</#list>
</@c.page>
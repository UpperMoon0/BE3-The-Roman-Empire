<%@page import="model.Region"%>
<%@page import="com.nhat.util.ViewUtil"%>
<%@page import="java.util.List"%>
<%-- regionSelection.jsp --%>

<form action="RegionView" method="post">
    <select name="selectedRegionId" size="5" onchange="this.form.submit()">
        <% 
            ViewUtil.updateRegionsList(request);
            List<Region> regions = (List<Region>) request.getAttribute("regions");
            if (regions != null) {
                for (Region region : regions) {
        %>
                    <option value="<%= region.getId() %>"><%= region.getName() %></option>
        <%
                }
            }
        %>
    </select>
</form>

<div id="selectedRegionInfo">
    <% 
        // Retrieve the selected region from the request
        Region selectedRegion = (Region) request.getAttribute("selectedRegion");
        if (selectedRegion != null) {
    %>
        <h3>Selected Region Info</h3>
        <p>Name: <%= selectedRegion.getName() %></p>
        <p>Market value: <%= selectedRegion.getMarketValue() %></p>
        <p>Growth rate: <%= selectedRegion.getGrowth() %> %</p>
        <p>Revolt rate: <%= selectedRegion.getRevoltRate() %> %</p>
        <p>Food supply: <%= selectedRegion.getFoodSupply() %></p>
    <%
        }
    %>
</div>

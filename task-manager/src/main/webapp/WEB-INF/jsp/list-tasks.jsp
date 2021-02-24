<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-tasks">Add
			Task</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Tasks</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="20%">Task Name</th>
						<th width="20%">Start Date</th>
						<th width="20%">End Date</th>
						<!-- <th width="15%">Task Description</th> -->
						<th width="20%">Severity</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="tasks">
						<tr>
							<td>${tasks.taskName}</td>
							<td><fmt:formatDate value="${tasks.startDate}"
									pattern="MM/dd/yyyy" /></td>
							<td><fmt:formatDate value="${tasks.endDate}"
									pattern="MM/dd/yyyy" /></td>

							<td>${tasks.severity}</td>
							<td><a type="button" class="btn btn-success"
								href="/update-tasks?id=${tasks.id}">Update</a> <a type="button"
								class="btn btn-warning" href="/delete-tasks?id=${tasks.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>
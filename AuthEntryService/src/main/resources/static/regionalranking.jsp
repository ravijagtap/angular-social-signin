<h1>Topic Lists</h1><a (click)="refresh()">Refresh</a>
<div [hidden]="!authenticated()">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Test Id</th>
				<th>Student Name</th>
				<th>Marks Obtained</th>
				<th>Completed In</th>
				<th>Q1</th>
				<th>Q2</th>
				<th>Q3</th>
				<th>Q4</th>
				<th>Q5</th>
				<th>Q6</th>
				<th>Q7</th>
				<th>Q8</th>
				<th>Q9</th>
				<th>Q10</th>
			</tr>
		</thead>
		<tbody>
		<tr *ngFor="let region of regionalRanking.REGIONAL_RANKING let i = index">
			<td>{{region.testId}}</td>
		    <td>{{region.firstName}}</td>
		    <td>{{region.totalMarksObtained}}</td>
		    <td>{{region.completedIn}}</td>
			<td *ngFor="let result of region.RESULT let j = index">
				{{result}}
			</td>
		</tr>
		</tbody>
	</table>
</div>
<div [hidden]="authenticated()">
	<p><a href='/login'>Login</a> to see list of topics</p>
</div>

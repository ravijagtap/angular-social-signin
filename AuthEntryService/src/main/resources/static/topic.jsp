<h1>Topic Lists</h1><a (click)="refresh()">Refresh</a>
<div [hidden]="!authenticated()">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Topic Name</th>
				<th>Your Average Score for last 3 tests</th>
				<th>Average Scores in your region</th>
			</tr>
		</thead>
		<tbody>
		<tr *ngFor="let score of scores.averageScores let i = index">
		    <td><a (click)="testrunner()">{{score.topicName}}</a></td>
		    <td>{{score.studentAverageScores}}</td>
		    <td>{{score.regionAverageScores}}</td>
		</tr>
		</tbody>
	</table>
</div>
<div [hidden]="authenticated()">
	<p><a href='/login'>Login</a> to see list of topics</p>
</div>

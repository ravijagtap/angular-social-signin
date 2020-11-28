<!doctype html>
<html>
	<head>
		<title>Hello AngularJS</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
    	<script src="myBucket.js"></script>
	</head>

	<body>
 	<div >
        <b>Q{{question.questionSequence}}. {{question.questionDescription}}</b>
        <div>
            <img src="data:image/JPEG;base64,{{question.figure}}" alt="img" />   
        </div>
    </div>
    <div>
    </div>
    <table class="table table-striped">
    	<thead>
			<tr>
				<th width="10%">Options</th>
				<th width="90%"></th>
			</tr>
		</thead>
		<tbody>
			<tr *ngFor="let q of question.answerOptions ; let i = index">
		    	<td><input type="radio" name="radio" [value]="q.answerId" (change)="onSelectionChange(q.answerId)" [checked]="q.answerId === question.answersGiven[0]"></td>
		    	<td id="{{q.answerId}}">{{q.answerText}}</td>
			</tr>
			<tr>
				<td><button type="button" class="btn btn-primary" (click)="previousQuestion()" >Previous</button></td>
				<td align="right"><button type="button" class="btn btn-primary" (click)="nextQuestion()" >Next</button></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
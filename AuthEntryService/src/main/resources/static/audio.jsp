<!doctype html>
<html>
	<head>
		<title>Hello AngularJS</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
    	<script src="myBucket.js"></script>
	</head>

	<body>
 	<div>
        <b>Q{{question.questionSequence}}. {{question.questionSummary}} {{question.questionDescription}}</b>
        <div>
        	<audio src="{{question.audioFilePath}}" controls class="link"></audio>    
        </div>
    </div>
    <div>
    </div>
    <table>
    	<thead>
			<tr>
				<th width="10%"></th>
				<th width="90%"></th>
			</tr>
		</thead>
		<tbody>
			<tr>
		    	<td><input type="text" name="spelling" id="spelling" style="text-transform:uppercase" (change)="onSelectionChange()" value="{{question.answersGiven}}"><br><br><br></td>
			</tr>
			<tr>
				<td><button type="button" class="btn btn-primary" (click)="previousQuestion()" >Previous</button></td>
				<td align="right"><button type="button" class="btn btn-primary" (click)="nextQuestion()" >Next</button></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
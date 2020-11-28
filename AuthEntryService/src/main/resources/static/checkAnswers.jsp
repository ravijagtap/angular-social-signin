<!doctype html>
<html>
	<head>
		<title>Hello AngularJS</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
    	<script src="myBucket.js"></script>
	</head>

	<body>
	<div style="width: 50%; border: 1px solid red">
	<b>{{checkAnswerJSON.ERROR}}</b>
	<table class="table table-striped" style="border: 1px solid blue">
		<tbody>
			<tr>
		    	<td *ngFor="let q of checkAnswerJSON.QUESTION_RESULT; let i = index" style="border: 1px solid {{q}}">
		    		{{i + 1}} - {{q}}
		    	</td>
			</tr>
		</tbody>
	</table>
	</div>
</body>
</html>
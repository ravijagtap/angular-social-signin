var AppService = ng.core.Injectable({}).Class({constructor: [ng.http.Http, function(http) {

    var self = this;
    this.authenticated = false;
    this.authenticate = function(credentials, callback) {

        var headers = credentials ? {
            authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)
        } : {};
        http.get('authuser', {headers: headers}).subscribe(function(response) {
            if (response.json().name) {
                self.authenticated = true;
            } else {
                self.authenticated = false;
            }
            callback && callback();
        });

    }

}]})

var HomeComponent = ng.core.Component({
    templateUrl : 'home.html'
}).Class({
    constructor : [AppService, ng.http.Http, function(app, http) {
        var self = this;
        this.greeting = {id:'', msg:''};
        http.get('homepage').subscribe(response => self.greeting =response.json());
        this.authenticated = function() { return app.authenticated; };
    }]
});

var StudentComponent = ng.core.Component({
    templateUrl : 'student.html'
}).Class({
    constructor : [AppService, ng.http.Http, function(app, http) {
    	var self = this;
        this.students = {id:'', firstName:'', lastName:''};
        if ( app.authenticated == true ){
        	http.get('user').subscribe(response => self.students =response.json());
        }
        
        this.authenticated = function() { return app.authenticated; };
        this.refresh = function() {
        	if ( app.authenticated == true ) {
        		http.get('user').subscribe(response => self.students =response.json());
        	}
        };
    }]
});

var TopicComponent = ng.core.Component({
    templateUrl : 'topic.jsp'
}).Class({
    constructor : [AppService, ng.http.Http, ng.router.Router, function(app, http, router) {
    	var self = this;
        this.scores = {topicName:'', studentAverageScores:'', regionAverageScores:''};
        if ( app.authenticated == true ){
        	http.get('gettopicaverage/1').subscribe(response => self.scores = response.json());
        }
        
        this.authenticated = function() { return app.authenticated; };
        this.refresh = function() {
        	if ( app.authenticated == true ) {
        		http.get('gettopicaverage/1').subscribe(response => self.scores = response.json());
        	}
        };
        
        this.testrunner = function() {
        	if ( app.authenticated == true ) {
        		router.navigateByUrl('/testrunner');
        	}
        };
    }]
});

var RegionalRankingComponent = ng.core.Component({
    templateUrl : 'regionalranking.jsp'
}).Class({
    constructor : [AppService, ng.http.Http, ng.router.Router, function(app, http, router) {
    	var self = this;
        this.regionalRanking = {topicName:'', studentAverageScores:'', regionAverageScores:''};
        if ( app.authenticated == true ){
        	http.get('regionalranking/6').subscribe(response => self.regionalRanking = response.json());
        }
        
        this.refresh = function() {
        	if ( app.authenticated == true ) {
        		http.get('regionalranking/6').subscribe(response => self.regionalRanking =response.json());
        	}
        };
        
        this.authenticated = function() { return app.authenticated; };
        
    }]
});

var CheckAnswersComponent = ng.core.Component({
    templateUrl : 'checkAnswers.jsp'
}).Class({
    constructor : [AppService, ng.http.Http, ng.router.Router, function(app, http, router) {
    	app.authenticated = true;
    	var self = this;
    	var answersGiven = [];
        this.question =         
        {
            testType: null,
            subjectId: null,
            topicId: null,
            testId: 25,
            questionId: null,
            difficultyLevel: 0,
            questionSequence: 0,
            navigateToQuestionSequence: null,
            questionDescription: null,
            answerOptions: null,
            answersGiven: null,
            action: null,
            dirtyObject: false,
            errorDescription: null,
            examMasterId: null,
            isNonVerbal: true,
            figure: null,
            isMultiChoice: null
        };
        
        this.checkAnswerJSON = {
        	COMPLETED_IN: null,
        	COMPLETOIN_DATE: null,
        	QUESTION_RESULT: null,
        	TESTDETAIL_DTO: this.question
        } ;
        
        http.post('checkanswers', this.question).subscribe(
        		response => { 
        			self.checkAnswerJSON = response.json();
        			if(self.checkAnswerJSON.ERROR) {
        				alert("Unauthorized");
        			} else {
        				self.question = self.checkAnswerJSON.TESTDETAIL_DTO;
        			}
        			 
        			}
        		);
        
        this.authenticated = function() { return app.authenticated; };
        
        this.nextQuestion = function() {
        	if ( app.authenticated == true ) {
        		this.question.action = "NEXT";
        		http.post('checkanswers', this.question).subscribe(response => self.question =response.json());
        	}
        };
        
        this.previousQuestion = function() {
        	if ( app.authenticated == true ) {
        		this.question.action = "PREVIOUS";
        		http.post('checkanswers', this.question).subscribe(response => self.question =response.json());
        	}
        };
    }]
});

var AudioComponent = ng.core.Component({
    templateUrl : 'audio.jsp'
}).Class({
    		constructor : [AppService, ng.http.Http, function(app, http) {
    			app.authenticated = true;
    	    	var self = this;
    	    	var answersGiven = [];
    	        this.question =         
    	        {
    	        		testType:"AUDIO",
    	        		subjectId:null,
    	        		topicId:null,
    	        		testId:null,
    	        		questionId:null,
    	        		difficultyLevel:0,
    	        		questionSequence:0,
    	        		navigateToQuestionSequence:null,
    	        		questionDescription:null,
    	        		answerOptions:null,
    	        		answersGiven:null,
    	        		action: "NEXT",
    	        		dirtyObject:false,
    	        		errorDescription:null,
    	        		examMasterId:null,
    	        		isNonVerbal:false,
    	        		figure:null,
    	        		isMultiChoice:false,
    	        		isCorrect:null,
    	        		correctAnswerText:null,
    	        		completedIn:null,
    	        		audioFilePath:null
    	        };
    	        
    	        http.post('spellcheck', this.question).subscribe(response => self.question =response.json());
    	        
    	        this.authenticated = function() { return app.authenticated; };
    	        
    	        this.nextQuestion = function() {
    	        	if ( app.authenticated == true ) {
    	        		this.question.action = "NEXT";
    	        		http.post('spellcheck', this.question).subscribe(response => self.question =response.json());
    	        	}
    	        };
    	        
    	        this.previousQuestion = function() {
    	        	if ( app.authenticated == true ) {
    	        		this.question.action = "PREVIOUS";
    	        		http.post('spellcheck', this.question).subscribe(response => self.question =response.json());
    	        	}
    	        };
    	        
    	        
    	        
    	        this.onSelectionChange = function() {
    	        	var answerId = event.currentTarget.value;
    	        	if ( app.authenticated == true ) {
    	        		if(this.question.isMultiChoice == false) {
    	        			answersGiven = new Array(0);
    	        			answersGiven.push(answerId);
    	        			this.question.answersGiven = answersGiven;
    	        			this.question.dirtyObject = true;
    	        		}
    	        	}
    	        }; 
    	    }]
  });

var NonVerbalComponent = ng.core.Component({
    templateUrl : 'nonverbal.jsp'
}).Class({
	constructor : [AppService, ng.http.Http, function(app, http) {
		app.authenticated = true;
    	var self = this;
    	var answersGiven = [];
        this.question =         
        {
            testType: "TOPIC",
            subjectId: null,
            topicId: 4,
            testId: null,
            questionId: null,
            difficultyLevel: 0,
            questionSequence: 0,
            navigateToQuestionSequence: null,
            questionDescription: null,
            answerOptions: null,
            answersGiven: null,
            action: "NEXT",
            dirtyObject: false,
            errorDescription: null,
            examMasterId: null,
            isNonVerbal: true,
            figure: null,
            isMultiChoice: null
        };
        
        http.post('testrunner', this.question).subscribe(response => self.question =response.json());
        
        this.authenticated = function() { return app.authenticated; };
        
        this.nextQuestion = function() {
        	if ( app.authenticated == true ) {
        		this.question.action = "NEXT";
        		http.post('testrunner', this.question).subscribe(response => self.question =response.json());
        	}
        };
        
        this.previousQuestion = function() {
        	if ( app.authenticated == true ) {
        		this.question.action = "PREVIOUS";
        		http.post('testrunner', this.question).subscribe(response => self.question =response.json());
        	}
        };
        
        this.onSelectionChange = function(answerId) {
        	if ( app.authenticated == true ) {
        		if(this.question.isMultiChoice == false) {
        			answersGiven = new Array(0);
        			answersGiven.push(answerId);
        			this.question.answersGiven = answersGiven;
        			this.question.dirtyObject = true;
        		}
        	}
        }; 
    }]
});

var LoginComponent = ng.core.Component({
    templateUrl : 'login.html'
}).Class({
    constructor : [AppService, ng.router.Router, function(app, router) {
        var self = this;
        this.credentials = {username:'', password:''};
        this.login = function() {
            app.authenticate(self.credentials, function() {
                router.navigateByUrl('/')
            });
            return false;
        };
    }]
});

var AppComponent = ng.core.Component({
        templateUrl: 'app.html',
        selector: 'app',
        providers: [AppService]
    }).Class({
        constructor : [AppService, ng.http.Http, ng.router.Router, function(app, http, router){
            app.authenticate();
            this.logout = function() {
                http.post('logout', {}).finally(function() {
                    app.authenticated = false;
                    router.navigateByUrl('/login')
                }).subscribe();
            };
        }]
    });

var RequestOptionsService = ng.core.Class({
    extends: ng.http.BaseRequestOptions,
    constructor : function() {},
    merge: function(opts) {
        opts.headers = new ng.http.Headers(opts.headers ? opts.headers : {});
        opts.headers.set('X-Requested-With', 'XMLHttpRequest');
        return opts.merge(opts);
    }
});

var routes = [
    { path: '', pathMatch: 'full', redirectTo: 'home'},
    { path: 'home', component: HomeComponent},
    { path: 'login', component: LoginComponent},
    { path: 'student', component: StudentComponent},
    { path: 'topic', component: TopicComponent},
    { path: 'nonverbal', component: NonVerbalComponent},
    { path: 'checkAnswers', component: CheckAnswersComponent},
    { path: 'regionalRanking', component: RegionalRankingComponent},
    { path: 'audio', component: AudioComponent}
];

var AppModule = ng.core.NgModule({
    imports: [ng.platformBrowser.BrowserModule, ng.http.HttpModule,
            ng.router.RouterModule.forRoot(routes), ng.forms.FormsModule],
    declarations: [HomeComponent, LoginComponent, AppComponent, StudentComponent, TopicComponent, NonVerbalComponent, CheckAnswersComponent, RegionalRankingComponent, AudioComponent],
    providers : [{ provide: ng.http.RequestOptions, useClass: RequestOptionsService }],
    bootstrap: [AppComponent]
  }).Class({constructor : function(){}});

document.addEventListener('DOMContentLoaded', function() {
    ng.platformBrowserDynamic.platformBrowserDynamic().bootstrapModule(AppModule);
});
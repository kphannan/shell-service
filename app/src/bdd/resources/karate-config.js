

function setupEnvironment() {
	var env = karate.env; // get java system property 'karate.env'

	karate.log( 'karate.env system property was:', env );

	if ( !env ) {
		env = 'local'; // a custom 'intelligent' default
	}

	var config = { // base config JSON
		appId: 'my.app.id',
		appSecret: 'my.secret',
		authHost: 'http://localhost:8102',
		authRootPath: '/authenticate/token',
		host: 'http://localhost:8102',
		rootPath: '/loan/origination/throttle',
		baseUrl: 'http://localhost:8102/killroy/was/here'
	};
	const authHosts = new Map([
		 [ 'local',    'http://localhost:9005' ]
		,[ 'dev',      'http://localhost:8102' ]
		,[ 'qa',       'http://qa:8102' ]
		,[ 'int',      'http://integration:8102' ]
		,[ 'preprod',  'http://preprod:8102' ]
		,[ 'demo',     'http://sales:8102' ]
		,[ 'prod',     'http://domain:8102' ]
	]);

	config.authHost = authHosts.get( env );
	config.authBaseUrl = config.authHost + config.authRootPath;

	const hosts = new Map([
		 [ 'local',    'http://localhost:9005' ]
		,[ 'dev',      'http://localhost:8102' ]
		,[ 'qa',       'http://qa:8102' ]
		,[ 'int',      'http://integration:8102' ]
		,[ 'preprod',  'http://preprod:8102' ]
		,[ 'demo',     'http://sales:8102' ]
		,[ 'prod',     'http://domain:8102' ]
	]);

	config.host = hosts.get( env );
	config.baseUrl = config.host + config.rootPath;

	// don't waste time waiting for a connection or if servers don't respond within 5 seconds
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 5000);
	karate.configure( 'report', { showLog: true, showAllSteps: false, logPrettyRequest: true, logPrettyResponse: true } );

	karate.log( config );
	karate.log( 'baseURL: ', config.baseUrl );

	return config;
}
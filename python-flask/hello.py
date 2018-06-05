from flask import Flask, session, redirect, url_for, escape, request
from RedisOperator import RedisOperator
from RedisSession import RedisSessionInterface

app = Flask(__name__)
app.config['SESSION_COOKIE_NAME'] = "icovn-session-id"
app.session_interface = RedisSessionInterface()

redisOperator = RedisOperator()

# Set the secret key to some random bytes. Keep this really secret!
app.secret_key = b'_5#y2L"F4Q8z\n\xec]/'

@app.route("/hello")
def hello():
    return "Hello World!"

@app.route('/')
def index():
    if 'username' in session:
    	myUsername = redisOperator.get('loggedIn:' + str(session.sid))
    	
        return 'Logged in as %s %s' % (escape(session['username']), myUsername)
    return 'You are not logged in'

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        session['username'] = request.form['username']
        redisOperator.set('loggedIn:' + str(session.sid), request.form['username'])
        return redirect(url_for('index'))
    return '''
        <form method="post">
            <p><input type=text name=username>
            <p><input type=submit value=Login>
        </form>
    '''

@app.route('/logout')
def logout():
    # remove the username from the session if it's there
    session.pop('username', None)
    redisOperator.delete('loggedIn:' + str(session.sid))
    return redirect(url_for('index'))

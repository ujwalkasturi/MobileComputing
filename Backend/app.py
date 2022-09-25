import os
from flask import Flask, request

app = Flask(__name__)



@app.route('/', methods=['POST'])
def upload():
    
    #print("I am here")
    category = request.form['category']
    UPLOAD_FOLDER = r"C:\Personal\University\Sem-1\Mobile_computing\Flask\categories\\"
    UPLOAD_FOLDER = UPLOAD_FOLDER+category+"\\"
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

    file1 = request.files['image']
    path = os.path.join(app.config['UPLOAD_FOLDER'], file1.filename)
    file1.save(path)

    return (category)
    return "hi"


if __name__ == '__main__':
    app.run(host='0.0.0.0')
import os
from flask import Flask, request

app = Flask(__name__)


# @app.route('/', methods=['GET', 'POST'])
# def upload():
#     if request.method == 'POST':
#         if 'image' not in request.files:
#             return 'there is no file1 in form!'
        
#         category = request.form['category']
#         UPLOAD_FOLDER = r"C:\Personal\University\Sem-1\Mobile_computing\Flask\categories\\"
#         UPLOAD_FOLDER = UPLOAD_FOLDER+category+"\\"
#         app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

#         file1 = request.files['image']
#         path = os.path.join(app.config['UPLOAD_FOLDER'], file1.filename)
#         file1.save(path)
#         return path
        
#     return '''
#     <h1>Upload new File</h1>
#     <form method="post" enctype="multipart/form-data">
#       <input type="file" name="image">
#       <input type="form" name="category">Enter Cat </input>
#       <input type="submit">
#     </form>
#     '''
# @app.route('/upload', methods=['GET', 'POST'])
# def upload_file():
#     if request.method == 'POST':
#         if 'image' not in request.files:
#             return 'there is no file1 in form!'
        
#         category = request.form['category']
#         UPLOAD_FOLDER = r"C:\Personal\University\Sem-1\Mobile_computing\Flask\categories\\"
#         UPLOAD_FOLDER = UPLOAD_FOLDER+category+"\\"
#         app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

#         file1 = request.files['image']
#         path = os.path.join(app.config['UPLOAD_FOLDER'], file1.filename)
#         file1.save(path)
#         return path

@app.route('/',methods=['POST'])
def work():
    
    print("I am here")
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
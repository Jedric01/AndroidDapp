from flask import Flask, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)


client = MongoClient("mongodb+srv://Admin:admin08082020@cluster0.fcbmh.mongodb.net/DappDb?retryWrites=true&w=majority")
db = client['DappDb']
gigCollection = db['Gigs']
userCollection = db['Users']
requestCollection = db['Requests']

@app.route("/")
def default():
    return "hello!"

@app.route("/api/users/count")
def api_userCount():
    return str(userCollection.count())

@app.route("/api/gigs/count")
def api_gigCount():
    return str(gigCollection.count())

@app.route("/api/users/index")
def api_userIndex():
    print(request.args['_id'])
    return userCollection.find_one({"_id": int(request.args['_id'])})

@app.route("/api/gigs/index")
def api_gigIndex():
    return gigCollection.find_one({"_id": int(request.args['_id'])})

@app.route("/api/requests/index")
def api_requestIndex():
    print(request.args["_id"])
    return requestCollection.find_one({"_id": int(request.args['_id'])})


@app.route("/api/users/insert", methods=['POST'])
def insertNewUser():
    newUser = {"_id": userCollection.count(), "_username": request.args["_username"],
                "_requestRef": [], "_orderRef" : []}
    userCollection.insert_one(newUser)
    return "success"

@app.route("/api/gigs/insert", methods=['POST'])
def api_gigInsert():
    id = request.args["_merchantID"]

    print(type(id))
    newDoc = {"_id": gigCollection.count(), 
        "_gigTitle": request.args["_gigTitle"], 
        "_gigDesc": request.args["_gigDesc"],
        "_price": int(request.args["_price"]),
        "_merchantID": int(id),}
    gigCollection.insert_one(newDoc)

    return "true"

@app.route("/api/requests/insert", methods=['POST'])
def api_requestInsert():
    newRequest = {"_id": requestCollection.count(),
        "_buyerID": int(request.args["_buyerID"]),
        "_gigID" : int(request.args["_gigID"]),
        "_status": request.args["_status"]}

    requestCollection.insert_one(newRequest)

    return str(requestCollection.count() - 1)

@app.route("/api/users/update/requestRef", methods=['POST'])
def api_userPushRequestRef():
    userCollection.update_one({"_id": int(request.args["_id"])}, {'$push': {'_requestRef': int(request.args["_requestRef"])}})
    return "success"

@app.route("/api/users/update/orderRef", methods = ['POST'])
def api_userPushOrderRef():
    userCollection.update_one({"_id": int(request.args["_id"])}, {'$push': {'_orderRef': int(request.args["_orderRef"])}})
    return "success"

@app.route("/api/requests/update/status", methods = ['POST'])
def api_requestUpdateStatus():
    print(request.args["_status"])
    requestCollection.update_one({"_id": int(request.args["_id"])}, { '$set': {"_status": request.args["_status"]}})
    return "success"

from flask import Flask, request, jsonify, abort
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.exc import IntegrityError
from py_eureka_client import eureka_client
import jwt
from marshmallow import Schema, fields

app = Flask(__name__)
app.config['JSON_SORT_KEYS'] = True

ROUTE = '/terminals'

# Конфигурация базы данных
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:123@localhost:5432/TerminalDB'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

# Модель Terminal
class Terminal(db.Model):
    __tablename__ = 'terminal'
    
    id = db.Column(db.Integer, primary_key=True)
    bankNo = db.Column('bank_no', db.Integer)
    model = db.Column(db.String)
    sN = db.Column('s_n', db.String)
    wireless = db.Column(db.Boolean)
    eth = db.Column(db.Boolean)
    wifi = db.Column(db.Boolean)
    gprs = db.Column(db.Boolean)
    contractId = db.Column('contract_id', db.Integer)

# Схема для сериализации
class TerminalSchema(Schema):
    id = fields.Int()
    bankNo = fields.Int()
    model = fields.Str()
    sN = fields.Str()
    wireless = fields.Bool()
    eth = fields.Bool()
    wifi = fields.Bool()
    gprs = fields.Bool()
    contractId = fields.Int()


def token_required(f):
    def decorated(*args, **kwargs):
        token = request.headers["Authorization"].split(" ")[1]
        if not token:
            return abort(403, 'Token is missing')
        try:
            jwt.decode(token, app.config['secret_key'], algorithms="HS256")
        except Exception as _:
            return abort(403, 'token is invalid/expired')
        return f(*args, **kwargs)
    return decorated


# Получить список всех терминалов
@app.route(ROUTE, methods=['GET'], endpoint='get_all_terminals')
@token_required
def get_all_terminals():
    terminals = Terminal.query.all()
    terminal_schema = TerminalSchema(many=True)
    result = terminal_schema.dump(terminals)
    print(result)
    print(jsonify(result))
    return jsonify(result)

# Получить терминал по ID
@app.route(ROUTE + '/<int:id>', methods=['GET'], endpoint='get_terminal_by_id')
@token_required
def get_terminal_by_id(id):
    terminal = Terminal.query.get(id)
    if terminal:
        terminal_schema = TerminalSchema()
        result = terminal_schema.dump(terminal)
        return jsonify(result)
    return jsonify({'message': 'Terminal not found'}), 404

# Получить список терминалов для контракта
@app.route(ROUTE + '/contract/<int:id>', methods=['GET'], endpoint='get_terminals_by_contract_id')
@token_required
def get_terminals_by_contract_id(id):
    try:
        terminals = Terminal.query.filter_by(contractId=id).all()
        terminal_schema = TerminalSchema(many=True)
        result = terminal_schema.dump(terminals)
        print(result)
        print(jsonify(result))
        return jsonify(result)
    except Exception as e:
        print(f"Error occurred: {e}")
        return jsonify([]), 500 

# Получить список терминалов на складе
@app.route(ROUTE + '/storage', methods=['GET'], endpoint='get_storage')
@token_required
def get_storage():
    terminals = Terminal.query.filter(Terminal.contractId.is_(None)).all()
    terminal_schema = TerminalSchema(many=True)
    result = terminal_schema.dump(terminals)
    return jsonify(result)

# Создать терминал
@app.route(ROUTE, methods=['POST'], endpoint='create_terminal')
@token_required
def create_terminal():
    data = request.json
    new_terminal = Terminal(
        bankNo=data.get('bankNo'),
        model=data.get('model'),
        sN=data.get('sN'),
        wireless=data.get('wireless'),
        eth=data.get('eth'),
        wifi=data.get('wifi'),
        gprs=data.get('gprs'),
        contractId=data.get('contractId')
    )
    db.session.add(new_terminal)
    try:
        db.session.commit()
        terminal_schema = TerminalSchema()
        result = terminal_schema.dump(new_terminal)
        return jsonify(result), 201
    except IntegrityError:
        db.session.rollback()
        return jsonify({'message': 'Error creating terminal'}), 400

# Изменить терминал
@app.route(ROUTE, methods=['PUT'], endpoint='update_terminal')
@token_required
def update_terminal():
    data = request.json
    terminal = Terminal.query.get(data.get('id'))
    if terminal:
        terminal.bankNo = data.get('bankNo')
        terminal.model = data.get('model')
        terminal.sN = data.get('sN')
        terminal.wireless = data.get('wireless')
        terminal.eth = data.get('eth')
        terminal.wifi = data.get('wifi')
        terminal.gprs = data.get('gprs')
        terminal.contractId = data.get('contractId')
        db.session.commit()
        terminal_schema = TerminalSchema()
        result = terminal_schema.dump(terminal)
        return jsonify(result)
    return jsonify({'message': 'Terminal not found'}), 404

# Удалить терминал
@app.route(ROUTE + '/<int:id>', methods=['DELETE'], endpoint='delete_terminal')
@token_required
def delete_terminal(id):
    terminal = Terminal.query.get(id)
    if terminal:
        db.session.delete(terminal)
        db.session.commit()
        return jsonify({'message': 'Terminal deleted'}), 204
    return jsonify({'message': 'Terminal not found'}), 404


if __name__ == '__main__':
    host = '127.0.0.1'
    port = 5000
    app.config['secret_key'] = '15169f6a-6ff4-4c74-94f7-6a760a87a44d'
    eureka_client.init(eureka_server="http://localhost:8761/eureka",
                   app_name="terminal",
                   instance_ip=host,
                   instance_port=port)
    app.run(host=host, port = port)

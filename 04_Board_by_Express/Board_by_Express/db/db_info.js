module.exports = (function () {
    return {
        local: {
            host: "localhost",
            port:3306,
            database:"board_by_express",
            user: "root",
            password:"mysql123",
        },
        real: {
            host: '',
            port: '',
            user: '',
            password: '',
            database: ''
        },
        staging: {
            host: '',
            port: '',
            user: '',
            password: '',
            database: ''
        },
        dev: {
            host: '',
            port: '',
            user: '',
            password: '',
            database: ''
        }
    }
})();
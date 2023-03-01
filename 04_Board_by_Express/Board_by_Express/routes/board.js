const express = require("express");
const router = express.Router();
const mysql_odbc = require("../db/db_conn");
const conn = mysql_odbc();

//자동으로 1페이지로 보내기
router.get('/list', function(req, res, next) {
    res.redirect('/board/list/1');
    console.log("왜안됨?")
});;

router.get('/list/:page', function(req, res, next) {
    const page = req.params.page;
    const sql = "select b_id, u_id, title," +
        "date_format(regdate,'%m-%d %H:%i') regdate, hit from boards";
    conn.query(sql, function (err, rows) {
        if (err) console.error("err : " + err);
        res.render('list', {title: ' 자유게시판', rows: rows});
    });
});


router.get('/write', function(req,res,next){
    res.render('write',{title : "게시판 글 쓰기"});
});

router.post('/write', function(req,res,next){
    var name = req.body.name;
    var title = req.body.title;
    var content = req.body.content;
    var datas = [name,title,content];

    var sql = "insert into boards(u_id, title, content, regdate, hit) values(?,?,?,now(),0)";
    conn.query(sql,datas, function (err, rows) {
        if (err) console.error("err : " + err);
        res.redirect('/board/list');
    });
});

router.get('/read/:idx',function(req,res,next)
{
    var idx = req.params.idx;
    var sql = "select u_id from boards where b_id=?";
    conn.query(sql,[idx], function(err,rows)
    {
        if(err) console.error(err);
        res.render('read', {title:"글 상세", row:rows[0]});
    });
});

// router.post('/update',function(req,res,next)
// {
//     var idx = req.body.idx;
//     var name = req.body.name;
//     var title = req.body.title;
//     var content = req.body.content;
//     var passwd = req.body.passwd;
//     var datas = [name,title,content,idx,passwd];
//
//     var sql = "update board set name=? , title=?,content=?, modidate=now() where idx=? and passwd=?";
//     conn.query(sql,datas, function(err,result)
//     {
//         if(err) console.error(err);
//         if(result.affectedRows == 0)
//         {
//             res.send("<script>alert('패스워드가 일치하지 않습니다.');history.back();</script>");
//         }
//         else
//         {
//             res.redirect('/board/read/'+idx);
//         }
//     });
// });
//
// router.get('/page/:page',function(req,res,next)
// {
//     var page = req.params.page;
//     var sql = "select idx, name, title, date_format(modidate,'%Y-%m-%d %H:%i:%s') modidate, " +
//         "date_format(regdate,'%Y-%m-%d %H:%i:%s') regdate,hit from board";
//     conn.query(sql, function (err, rows) {
//         if (err) console.error("err : " + err);
//         res.render('page', {title: ' 게시판 리스트', rows: rows, page:page, length:rows.length-1, page_num:10});
//         console.log(rows.length-1);
//     });
// });
//
// router.post('/delete',function(req,res,next)
// {
//     var idx = req.body.idx;
//     var passwd = req.body.passwd;
//     var datas = [idx,passwd];
//
//     var sql = "delete from board where idx=? and passwd=?";
//     conn.query(sql,datas, function(err,result)
//     {
//         if(err) console.error(err);
//         if(result.affectedRows == 0)
//         {
//             res.send("<script>alert('패스워드가 일치하지 않습니다.');history.back();</script>");
//         }
//         else
//         {
//             res.redirect('/board/list/');
//         }
//     });
// });

module.exports = router;
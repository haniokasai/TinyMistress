/**
 * Created by hani on 2017/03/24.
 */
//ログを随時更新
//http://qiita.com/momo298/items/f90c4ef4c6bff7fe7ca2
$.PeriodicalUpdater('http://localhost:11111/info/log',{
        method: 'get',
        minTimeout: 1000,
        type: 'html',
        multiplier:1,
        maxCalls: 0
    },
    function(data) {
        $('#log_display').append(data);
    });
/**
 * Created by hani on 2017/03/24.
 */
    <!--サーバー稼働状態-->
        //http://qiita.com/momo298/items/f90c4ef4c6bff7fe7ca2
        $.PeriodicalUpdater('http://localhost:11111/info/status',{
                method: 'get',
                minTimeout: 1000,
                type: 'html',
                multiplier:1,
                maxCalls: 0
            },
            function(data){
                var tx;
                if(data.toString().match(/true/)){
                    tx="サーバー: 稼働中";
                }else{
                    tx="サーバー: 停止中";
                }
                $("#ajax_txt").text(tx);
            });
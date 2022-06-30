$(function(){
//参照divを表示して、編集divを隠す。
  $('.show_mode').show();
  $('.edit_mode').hide();
  
  	//編集ボタン押下
	$('#edit_btn').on('click',function(){
  		//参照divを隠して、編集divを表示する。
  		$('.show_mode').hide();
 		$('.edit_mode').show();
	})
});

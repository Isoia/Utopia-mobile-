package view.controller;

import java.util.HashMap;
import java.util.Map;

import view.manager.LoginManagerController;
import view.manager.MoveToManageMenu;
import view.manager.SelectManagerMenuController;
import view.manager.menu.AddManagerController;
import view.manager.menu.DeleteFoodNewsController;
import view.manager.menu.DeleteManagerController;
import view.manager.menu.DeletePostListController;
import view.manager.menu.DeleteUserController;
import view.manager.menu.UploadFoodNewsController;
import view.post.DeletePostController;
import view.post.GetPostController;
import view.post.GetPostListController;
import view.post.GetRandomPostController;
import view.post.UpdateAfterPostController;
import view.post.UpdateBeforePostController;
import view.post.WriteCommentController;
import view.post.WritePostController;
import view.user.LoadMyPageController;
import view.user.LoginController;
import view.user.LogoutController;
import view.user.RegisterController;
import view.user.UpdateUserController;

public class HandlerMapping {
	private Map<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		// User
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/register.do", new RegisterController());
		mappings.put("/user-update.do", new UpdateUserController());
		mappings.put("/user-mypage.do", new LoadMyPageController());
		
		// Post
		mappings.put("/post-write.do", new WritePostController());
		mappings.put("/post.do", new GetPostController());
		mappings.put("/postlist.do", new GetPostListController());
		
		mappings.put("/post-delete.do", new DeletePostController());
		mappings.put("/post-update-before.do", new UpdateBeforePostController());
		mappings.put("/post-update-after.do", new UpdateAfterPostController());
		
		mappings.put("/post-random.do", new GetRandomPostController()); // 날씨별 랜덤 추천
		
		mappings.put("/comment-write.do", new WriteCommentController());
		
		// Manager
		mappings.put("/manage.do", new MoveToManageMenu());
		mappings.put("/manage-login.do", new LoginManagerController());
		mappings.put("/manage-sel.do", new SelectManagerMenuController());
		
		mappings.put("/manage-manager-add.do", new AddManagerController());
		mappings.put("/manage-manager-delete.do", new DeleteManagerController());
		
		mappings.put("/manage-user-delete.do", new DeleteUserController());
		
		mappings.put("/manage-postlist-delete.do", new DeletePostListController());
		
		mappings.put("/manage-foodnews-upload.do", new UploadFoodNewsController());
		mappings.put("/manage-foodnews-delete.do", new DeleteFoodNewsController());
	}

	public Controller getController(String path) {
		return mappings.get(path);
	}
}


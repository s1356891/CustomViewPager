package com.workspace.bin.customviewpager;

import com.google.gson.Gson;

import java.util.List;

//广告轮播对象类
public class GuangGao {

	private int count;
	private int err;
	private int total;
	private int page;
	private int refresh;
	private List<ItemsBean> items;

	public static GuangGao objectFromData(String str) {

		return new Gson().fromJson(str, GuangGao.class);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getErr() {
		return err;
	}

	public void setErr(int err) {
		this.err = err;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRefresh() {
		return refresh;
	}

	public void setRefresh(int refresh) {
		this.refresh = refresh;
	}

	public List<ItemsBean> getItems() {
		return items;
	}

	public void setItems(List<ItemsBean> items) {
		this.items = items;
	}

	public static class ItemsBean {
		/**
		 * format : word
		 * image :
		 * published_at : 1489541701
		 * tag :
		 * user : {"avatar_updated_at":1469635177,"medium":"//pic.qiushibaike.com/system/avtnew/3093/30936317/medium/201607272359376.JPEG","thumb":"//pic.qiushibaike.com/system/avtnew/3093/30936317/thumb/201607272359376.JPEG","last_visited_at":1452358061,"created_at":1452358061,"updated_at":1484494498,"state":"active","role":"","login":"流星，音晴未雨","last_device":"android_9.0.2","id":30936317,"icon":"201607272359376.JPEG"}
		 * image_size : null
		 * id : 118720950
		 * votes : {"down":-9,"up":498}
		 * created_at : 1489536681
		 * content : 有次去医院打针，护士姐姐扎了三针没扎进去，媳妇在一旁看不下去了边说“妹啊，外面那些吸 du 的都比你老练，一扎一个准啊”。 我本来疼得要死却也给逗乐了。。。。
		 * state : publish
		 * comments_count : 25
		 * allow_comment : true
		 * share_count : 6
		 * type : hot
		 */

		private String format;
		private String image;
		private int published_at;
		private String tag;
		private UserBean user;
		private Object image_size;
		private int id;
		private VotesBean votes;
		private int created_at;
		private String content;
		private String state;
		private int comments_count;
		private boolean allow_comment;
		private int share_count;
		private String type;

		public static ItemsBean objectFromData(String str) {

			return new Gson().fromJson(str, ItemsBean.class);
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public int getPublished_at() {
			return published_at;
		}

		public void setPublished_at(int published_at) {
			this.published_at = published_at;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public UserBean getUser() {
			return user;
		}

		public void setUser(UserBean user) {
			this.user = user;
		}

		public Object getImage_size() {
			return image_size;
		}

		public void setImage_size(Object image_size) {
			this.image_size = image_size;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public VotesBean getVotes() {
			return votes;
		}

		public void setVotes(VotesBean votes) {
			this.votes = votes;
		}

		public int getCreated_at() {
			return created_at;
		}

		public void setCreated_at(int created_at) {
			this.created_at = created_at;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public int getComments_count() {
			return comments_count;
		}

		public void setComments_count(int comments_count) {
			this.comments_count = comments_count;
		}

		public boolean isAllow_comment() {
			return allow_comment;
		}

		public void setAllow_comment(boolean allow_comment) {
			this.allow_comment = allow_comment;
		}

		public int getShare_count() {
			return share_count;
		}

		public void setShare_count(int share_count) {
			this.share_count = share_count;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public static class UserBean {
			/**
			 * avatar_updated_at : 1469635177
			 * medium : //pic.qiushibaike.com/system/avtnew/3093/30936317/medium/201607272359376.JPEG
			 * thumb : //pic.qiushibaike.com/system/avtnew/3093/30936317/thumb/201607272359376.JPEG
			 * last_visited_at : 1452358061
			 * created_at : 1452358061
			 * updated_at : 1484494498
			 * state : active
			 * role :
			 * login : 流星，音晴未雨
			 * last_device : android_9.0.2
			 * id : 30936317
			 * icon : 201607272359376.JPEG
			 */

			private int avatar_updated_at;
			private String medium;
			private String thumb;
			private int last_visited_at;
			private int created_at;
			private int updated_at;
			private String state;
			private String role;
			private String login;
			private String last_device;
			private int id;
			private String icon;

			public static UserBean objectFromData(String str) {

				return new Gson().fromJson(str, UserBean.class);
			}

			public int getAvatar_updated_at() {
				return avatar_updated_at;
			}

			public void setAvatar_updated_at(int avatar_updated_at) {
				this.avatar_updated_at = avatar_updated_at;
			}

			public String getMedium() {
				return medium;
			}

			public void setMedium(String medium) {
				this.medium = medium;
			}

			public String getThumb() {
				return thumb;
			}

			public void setThumb(String thumb) {
				this.thumb = thumb;
			}

			public int getLast_visited_at() {
				return last_visited_at;
			}

			public void setLast_visited_at(int last_visited_at) {
				this.last_visited_at = last_visited_at;
			}

			public int getCreated_at() {
				return created_at;
			}

			public void setCreated_at(int created_at) {
				this.created_at = created_at;
			}

			public int getUpdated_at() {
				return updated_at;
			}

			public void setUpdated_at(int updated_at) {
				this.updated_at = updated_at;
			}

			public String getState() {
				return state;
			}

			public void setState(String state) {
				this.state = state;
			}

			public String getRole() {
				return role;
			}

			public void setRole(String role) {
				this.role = role;
			}

			public String getLogin() {
				return login;
			}

			public void setLogin(String login) {
				this.login = login;
			}

			public String getLast_device() {
				return last_device;
			}

			public void setLast_device(String last_device) {
				this.last_device = last_device;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getIcon() {
				return icon;
			}

			public void setIcon(String icon) {
				this.icon = icon;
			}
		}

		public static class VotesBean {
			/**
			 * down : -9
			 * up : 498
			 */

			private int down;
			private int up;

			public static VotesBean objectFromData(String str) {

				return new Gson().fromJson(str, VotesBean.class);
			}

			public int getDown() {
				return down;
			}

			public void setDown(int down) {
				this.down = down;
			}

			public int getUp() {
				return up;
			}

			public void setUp(int up) {
				this.up = up;
			}
		}
	}
}

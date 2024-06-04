USE [master]
GO
/****** Object:  Database [HappyPrograming]    Script Date: 6/4/2024 8:29:09 AM ******/
CREATE DATABASE [HappyPrograming]

USE [HappyPrograming]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[accid] [int] IDENTITY(1,1) NOT NULL,
	[username] [nchar](50) NOT NULL,
	[password] [nchar](50) NOT NULL,
	[roleid] [int] NOT NULL,
	[email] [nchar](50) NOT NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[accid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feature]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feature](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[url] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Feature] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[id] [int] NOT NULL,
	[rate] [nchar](10) NULL,
	[comment] [nvarchar](max) NULL,
	[userid] [int] NOT NULL,
	[menid] [int] NOT NULL,
 CONSTRAINT [PK_Feedback] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mentor]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mentor](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[gender] [bit] NOT NULL,
	[phone] [nchar](10) NOT NULL,
	[address] [nvarchar](100) NOT NULL,
	[dateofbirth] [date] NULL,
	[ava] [nchar](10) NULL,
	[job] [nvarchar](50) NULL,
	[intro] [nvarchar](max) NULL,
	[achivement] [nvarchar](max) NULL,
	[experience] [nvarchar](max) NULL,
	[certificate] [nvarchar](max) NULL,
	[status] [bit] NOT NULL,
	[accid] [int] NOT NULL,
 CONSTRAINT [PK_Mentor] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mentor_Skill]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mentor_Skill](
	[mentorid] [int] NOT NULL,
	[skillid] [int] NOT NULL,
 CONSTRAINT [PK_Mentor_Skill] PRIMARY KEY CLUSTERED 
(
	[mentorid] ASC,
	[skillid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Request]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Request](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](100) NULL,
	[deadlineTime] [datetime] NULL,
	[skills] [nvarchar](100) NULL,
	[contentRequest] [nvarchar](max) NULL,
	[status] [nchar](10) NULL,
	[userid] [int] NOT NULL,
	[menid] [int] NOT NULL,
 CONSTRAINT [PK_Request] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role_Feature]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role_Feature](
	[roleid] [int] NOT NULL,
	[feaid] [int] NOT NULL,
 CONSTRAINT [PK_Role_Feature] PRIMARY KEY CLUSTERED 
(
	[roleid] ASC,
	[feaid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Session]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Session](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] NULL,
	[slotid] [int] NOT NULL,
	[status] [bit] NOT NULL,
	[skill] [nvarchar](100) NULL,
	[menid] [int] NOT NULL,
	[userid] [int] NULL,
 CONSTRAINT [PK_Session] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Skill]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Skill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[skillname] [nvarchar](50) NULL,
	[status] [bit] NOT NULL,
	[description] [nvarchar](max) NULL,
	[ava] [nvarchar](50) NULL,
 CONSTRAINT [PK_Skill] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Slot]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Slot](
	[id] [int] NOT NULL,
	[from] [time](7) NULL,
	[to] [time](7) NULL,
 CONSTRAINT [PK_Slot] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 6/4/2024 8:29:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[userid] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Gender] [bit] NULL,
	[Phone] [nchar](10) NULL,
	[Address] [nvarchar](100) NULL,
	[DateOfBirth] [date] NULL,
	[accid] [int] NOT NULL,
	[ava] [nvarchar](max) NULL,
 CONSTRAINT [PK_User_1] PRIMARY KEY CLUSTERED 
(
	[userid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 
GO
INSERT [dbo].[Account] ([accid], [username], [password], [roleid], [email], [status]) VALUES (3, N'admin11                                           ', N'123456                                            ', 2, N'vuvien73@gmail.com                                ', 1)
GO
INSERT [dbo].[Account] ([accid], [username], [password], [roleid], [email], [status]) VALUES (4, N'admin33                                           ', N'123456                                            ', 1, N'bu                                                ', 1)
GO
INSERT [dbo].[Account] ([accid], [username], [password], [roleid], [email], [status]) VALUES (10, N'admin89                                           ', N'123456                                            ', 1, N'hsdjs                                             ', 1)
GO
INSERT [dbo].[Account] ([accid], [username], [password], [roleid], [email], [status]) VALUES (11, N'admin                                             ', N'admin                                             ', 3, N'nhucut@gmail.com                                  ', 1)
GO
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (1, N'View profile of mentee', N'/profile')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (2, N'View request', N'/view')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (3, N'Create request', N'/request')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (4, N'List request', N'/list_request')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (5, N'Update request', N'/update_request')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (6, N'Dashboard controller', N'/admin')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (7, N'Update skill', N'/updateskill')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (8, N'Create schedule', N'/schedule')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (9, N'View mentor schedule', N'/view_schedule')
GO
INSERT [dbo].[Feature] ([id], [name], [url]) VALUES (10, N'Book mentor', N'/book_mentor')
GO
SET IDENTITY_INSERT [dbo].[Mentor] ON 
GO
INSERT [dbo].[Mentor] ([id], [name], [gender], [phone], [address], [dateofbirth], [ava], [job], [intro], [achivement], [experience], [certificate], [status], [accid]) VALUES (3, N'Vu', 1, N'0123456789', N'Lang son', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 4)
GO
INSERT [dbo].[Mentor] ([id], [name], [gender], [phone], [address], [dateofbirth], [ava], [job], [intro], [achivement], [experience], [certificate], [status], [accid]) VALUES (4, N'Vien', 1, N'012345678 ', N'laks', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 10)
GO
SET IDENTITY_INSERT [dbo].[Mentor] OFF
GO
INSERT [dbo].[Mentor_Skill] ([mentorid], [skillid]) VALUES (3, 1)
GO
INSERT [dbo].[Mentor_Skill] ([mentorid], [skillid]) VALUES (3, 2)
GO
INSERT [dbo].[Mentor_Skill] ([mentorid], [skillid]) VALUES (3, 3)
GO
INSERT [dbo].[Mentor_Skill] ([mentorid], [skillid]) VALUES (4, 1)
GO
INSERT [dbo].[Mentor_Skill] ([mentorid], [skillid]) VALUES (4, 2)
GO
INSERT [dbo].[Mentor_Skill] ([mentorid], [skillid]) VALUES (4, 3)
GO
SET IDENTITY_INSERT [dbo].[Request] ON 
GO
INSERT [dbo].[Request] ([id], [title], [deadlineTime], [skills], [contentRequest], [status], [userid], [menid]) VALUES (7, N'smd ksd', CAST(N'2024-06-21T16:42:00.000' AS DateTime), N'C Java ', N'skds ssdksd  skd', N'Processing', 4, 3)
GO
INSERT [dbo].[Request] ([id], [title], [deadlineTime], [skills], [contentRequest], [status], [userid], [menid]) VALUES (8, N'sd ksd  skddksndksd', CAST(N'2024-06-28T16:42:00.000' AS DateTime), N'C Java C# ', N'nhu lon tao', N'Processing', 4, 4)
GO
INSERT [dbo].[Request] ([id], [title], [deadlineTime], [skills], [contentRequest], [status], [userid], [menid]) VALUES (9, N'sd ksd  skddksndksd', CAST(N'2024-06-28T16:42:00.000' AS DateTime), N'C Java C# ', N'nhu lon tao', N'Processing', 4, 4)
GO
INSERT [dbo].[Request] ([id], [title], [deadlineTime], [skills], [contentRequest], [status], [userid], [menid]) VALUES (10, N'sjdnhu cut pn toa', CAST(N'2024-06-01T16:45:00.000' AS DateTime), N'C ', N'sd ssdksdsdsd s', N'Processing', 4, 3)
GO
SET IDENTITY_INSERT [dbo].[Request] OFF
GO
INSERT [dbo].[Role] ([id], [name]) VALUES (1, N'Mentor')
GO
INSERT [dbo].[Role] ([id], [name]) VALUES (2, N'Mentee')
GO
INSERT [dbo].[Role] ([id], [name]) VALUES (3, N'Admin')
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (1, 8)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (1, 9)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (2, 1)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (2, 2)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (2, 3)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (2, 4)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (2, 5)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (2, 10)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (3, 6)
GO
INSERT [dbo].[Role_Feature] ([roleid], [feaid]) VALUES (3, 7)
GO
SET IDENTITY_INSERT [dbo].[Session] ON 
GO
INSERT [dbo].[Session] ([id], [date], [slotid], [status], [skill], [menid], [userid]) VALUES (1, CAST(N'2024-06-05' AS Date), 1, 0, N'C', 4, 4)
GO
INSERT [dbo].[Session] ([id], [date], [slotid], [status], [skill], [menid], [userid]) VALUES (2, CAST(N'2024-06-06' AS Date), 1, 0, N'Java', 4, 4)
GO
SET IDENTITY_INSERT [dbo].[Session] OFF
GO
SET IDENTITY_INSERT [dbo].[Skill] ON 
GO
INSERT [dbo].[Skill] ([id], [skillname], [status], [description], [ava]) VALUES (1, N'C', 1, N'smdsmmd', N'C.jpg')
GO
INSERT [dbo].[Skill] ([id], [skillname], [status], [description], [ava]) VALUES (2, N'Java', 1, NULL, N'Java.png')
GO
INSERT [dbo].[Skill] ([id], [skillname], [status], [description], [ava]) VALUES (3, N'C#', 1, NULL, N'csharp.jpg')
GO
INSERT [dbo].[Skill] ([id], [skillname], [status], [description], [ava]) VALUES (5, N'Python', 1, NULL, N'python.jpg')
GO
INSERT [dbo].[Skill] ([id], [skillname], [status], [description], [ava]) VALUES (6, N'Javascript', 1, NULL, N'javascript.png')
GO
INSERT [dbo].[Skill] ([id], [skillname], [status], [description], [ava]) VALUES (7, N'AI', 1, NULL, N'AI.png')
GO
INSERT [dbo].[Skill] ([id], [skillname], [status], [description], [ava]) VALUES (9, N'HH', 1, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[Skill] OFF
GO
INSERT [dbo].[Slot] ([id], [from], [to]) VALUES (1, CAST(N'07:30:00' AS Time), CAST(N'09:50:00' AS Time))
GO
INSERT [dbo].[Slot] ([id], [from], [to]) VALUES (2, CAST(N'10:00:00' AS Time), CAST(N'12:20:00' AS Time))
GO
INSERT [dbo].[Slot] ([id], [from], [to]) VALUES (3, CAST(N'12:50:00' AS Time), CAST(N'15:10:00' AS Time))
GO
INSERT [dbo].[Slot] ([id], [from], [to]) VALUES (4, CAST(N'15:20:00' AS Time), CAST(N'17:40:00' AS Time))
GO
SET IDENTITY_INSERT [dbo].[User] ON 
GO
INSERT [dbo].[User] ([userid], [Name], [Gender], [Phone], [Address], [DateOfBirth], [accid], [ava]) VALUES (4, N'Vu Vien Chiennn', 1, N'0123456789', N'Lang Son', CAST(N'2024-05-30' AS Date), 3, N'Screenshot 2024-05-31 215521.png')
GO
SET IDENTITY_INSERT [dbo].[User] OFF
GO
/****** Object:  Index [IX_Account_1]    Script Date: 6/4/2024 8:29:10 AM ******/
CREATE NONCLUSTERED INDEX [IX_Account_1] ON [dbo].[Account]
(
	[roleid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Mentor]    Script Date: 6/4/2024 8:29:10 AM ******/
ALTER TABLE [dbo].[Mentor] ADD  CONSTRAINT [IX_Mentor] UNIQUE NONCLUSTERED 
(
	[accid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Mentor_Skill]    Script Date: 6/4/2024 8:29:10 AM ******/
CREATE NONCLUSTERED INDEX [IX_Mentor_Skill] ON [dbo].[Mentor_Skill]
(
	[mentorid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_User_1]    Script Date: 6/4/2024 8:29:10 AM ******/
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [IX_User_1] UNIQUE NONCLUSTERED 
(
	[accid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([roleid])
REFERENCES [dbo].[Role] ([id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Feedback_Mentor] FOREIGN KEY([menid])
REFERENCES [dbo].[Mentor] ([id])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK_Feedback_Mentor]
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Feedback_User] FOREIGN KEY([userid])
REFERENCES [dbo].[User] ([userid])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK_Feedback_User]
GO
ALTER TABLE [dbo].[Mentor]  WITH CHECK ADD  CONSTRAINT [FK_Mentor_Account] FOREIGN KEY([accid])
REFERENCES [dbo].[Account] ([accid])
GO
ALTER TABLE [dbo].[Mentor] CHECK CONSTRAINT [FK_Mentor_Account]
GO
ALTER TABLE [dbo].[Mentor_Skill]  WITH CHECK ADD  CONSTRAINT [FK_Mentor_Skill_Mentor] FOREIGN KEY([mentorid])
REFERENCES [dbo].[Mentor] ([id])
GO
ALTER TABLE [dbo].[Mentor_Skill] CHECK CONSTRAINT [FK_Mentor_Skill_Mentor]
GO
ALTER TABLE [dbo].[Mentor_Skill]  WITH CHECK ADD  CONSTRAINT [FK_Mentor_Skill_Skill] FOREIGN KEY([skillid])
REFERENCES [dbo].[Skill] ([id])
GO
ALTER TABLE [dbo].[Mentor_Skill] CHECK CONSTRAINT [FK_Mentor_Skill_Skill]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Mentor] FOREIGN KEY([menid])
REFERENCES [dbo].[Mentor] ([id])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Mentor]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_User] FOREIGN KEY([userid])
REFERENCES [dbo].[User] ([userid])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_User]
GO
ALTER TABLE [dbo].[Role_Feature]  WITH CHECK ADD  CONSTRAINT [FK_Role_Feature_Feature] FOREIGN KEY([feaid])
REFERENCES [dbo].[Feature] ([id])
GO
ALTER TABLE [dbo].[Role_Feature] CHECK CONSTRAINT [FK_Role_Feature_Feature]
GO
ALTER TABLE [dbo].[Role_Feature]  WITH CHECK ADD  CONSTRAINT [FK_Role_Feature_Role] FOREIGN KEY([roleid])
REFERENCES [dbo].[Role] ([id])
GO
ALTER TABLE [dbo].[Role_Feature] CHECK CONSTRAINT [FK_Role_Feature_Role]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_Mentor] FOREIGN KEY([menid])
REFERENCES [dbo].[Mentor] ([id])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_Mentor]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_Slot1] FOREIGN KEY([slotid])
REFERENCES [dbo].[Slot] ([id])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_Slot1]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_User] FOREIGN KEY([userid])
REFERENCES [dbo].[User] ([userid])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_User]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Account] FOREIGN KEY([accid])
REFERENCES [dbo].[Account] ([accid])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Account]
GO
USE [master]
GO
ALTER DATABASE [HappyPrograming] SET  READ_WRITE 
GO

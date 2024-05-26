USE [master]
GO
/****** Object:  Database [HappyPrograming]    Script Date: 5/24/2024 11:40:22 AM ******/
CREATE DATABASE [HappyPrograming]
USE [HappyPrograming]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 5/24/2024 11:40:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[accid] [int] NOT NULL,
	[username] [nchar](50) NOT NULL,
	[password] [nchar](50) NOT NULL,
	[role] [nchar](10) NOT NULL,
	[email] [nchar](50) NOT NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[accid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mentor]    Script Date: 5/24/2024 11:40:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mentor](
	[id] [int] NOT NULL,
	[ava] [nchar](10) NULL,
	[job] [nchar](10) NULL,
	[skill] [nchar](10) NULL,
	[intro] [nchar](10) NULL,
	[achivement] [nchar](10) NULL,
	[feedbackid] [nchar](10) NULL,
	[experience] [nchar](10) NULL,
	[certificate] [nchar](10) NULL,
	[profileid] [nchar](10) NULL,
	[status] [bit] NULL,
	[accid] [int] NOT NULL,
 CONSTRAINT [PK_Mentor] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_Mentor] UNIQUE NONCLUSTERED 
(
	[accid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mentor_Skill]    Script Date: 5/24/2024 11:40:22 AM ******/
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
/****** Object:  Table [dbo].[Skill]    Script Date: 5/24/2024 11:40:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Skill](
	[id] [int] NOT NULL,
	[skillname] [nvarchar](50) NULL,
	[status] [bit] NOT NULL,
	[description] [nvarchar](max) NULL,
 CONSTRAINT [PK_Skill] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 5/24/2024 11:40:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[userid] [int] NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Gender] [bit] NULL,
	[Phone] [nchar](10) NULL,
	[Address] [nvarchar](100) NULL,
	[DateOfBirth] [date] NULL,
	[accid] [int] NOT NULL,
 CONSTRAINT [PK_User_1] PRIMARY KEY CLUSTERED 
(
	[userid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_User_1] UNIQUE NONCLUSTERED 
(
	[accid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Index [IX_Mentor_Skill]    Script Date: 5/24/2024 11:40:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Mentor_Skill] ON [dbo].[Mentor_Skill]
(
	[mentorid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
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
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Account] FOREIGN KEY([accid])
REFERENCES [dbo].[Account] ([accid])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Account]
GO
USE [master]
GO
ALTER DATABASE [HappyPrograming] SET  READ_WRITE 
GO

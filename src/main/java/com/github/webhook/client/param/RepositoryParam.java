/*
 * Copyright © ${project.inceptionYear} organization lonecloud
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.webhook.client.param;

import lombok.Data;

import java.security.acl.Owner;
import java.util.Date;
import java.util.List;

/**
 * 仓库参数
 *
 * @author lonecloud
 * @version v1.0
 */
@Data
public class RepositoryParam {

    private Long id;
    private String nodeId;
    private String name;
    private String fullName;
    private Boolean privateResp;
    private Owner owner;
    private String htmlUrl;
    private String description;
    private Boolean fork;
    private String url;
    private String forksUrl;
    private String keysUrl;
    private String collaboratorsUrl;
    private String teamsUrl;
    private String hooksUrl;
    private String issueEventsUrl;
    private String eventsUrl;
    private String assigneesUrl;
    private String branchesUrl;
    private String tagsUrl;
    private String blobsUrl;
    private String gitTagsUrl;
    private String gitRefsUrl;
    private String treesUrl;
    private String statusesUrl;
    private String languagesUrl;
    private String stargazersUrl;
    private String contributorsUrl;
    private String subscribersUrl;
    private String subscriptionUrl;
    private String commitsUrl;
    private String gitCommitsUrl;
    private String commentsUrl;
    private String issueCommentUrl;
    private String contentsUrl;
    private String compareUrl;
    private String mergesUrl;
    private String archiveUrl;
    private String downloadsUrl;
    private String issuesUrl;
    private String pullsUrl;
    private String milestonesUrl;
    private String notificationsUrl;
    private String labelsUrl;
    private String releasesUrl;
    private String deploymentsUrl;
    private Date createdAt;
    private Date updatedAt;
    private Date pushedAt;
    private String gitUrl;
    private String sshUrl;
    private String cloneUrl;
    private String svnUrl;
    private String homepage;
    private Integer size;
    private Integer stargazersCount;
    private Integer watchersCount;
    private String language;
    private Boolean hasIssues;
    private Boolean hasProjects;
    private Boolean hasDownloads;
    private Boolean hasWiki;
    private Boolean hasPages;
    private Integer forksCount;
    private String mirrorUrl;
    private Boolean archived;
    private Boolean disabled;
    private Integer openIssuesCount;
    private String license;
    private Boolean allowForking;
    private Boolean isTemplate;
    private List<String> topics;
    private String visibility;
    private Integer forks;
    private Integer openIssues;
    private Integer watchers;
    private String defaultBranch;
}

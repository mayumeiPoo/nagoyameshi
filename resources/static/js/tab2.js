

{

const tabMenus = document.querySelectorAll('.tab__menu-item');
console.log(tabMenus);
tabMenus.forEach((tabMenu) => {
  tabMenu.addEventListener('click', (e) => {
    console.log('Tab clicked!');
    tabSwitch(e);
  });
});


function tabSwitch(e) {
	

  // クリックされた要素のデータ属性を取得
  const tabTargetData = e.currentTarget.dataset.tab;
  // クリックされた要素の親要素と、その子要素を取得
  const tabList = e.currentTarget.closest('.tab__menu');
  console.log(tabList);
  const tabItems = tabList.querySelectorAll('.tab__menu-item');
  console.log(tabItems);
  // クリックされた要素の親要素の兄弟要素の子要素を取得
  const tabPanelItems = tabList.
  nextElementSibling.querySelectorAll('.tab__panel-box');
  console.log(tabPanelItems);

  // クリックされたtabの同階層のmenuとpanelのクラスを削除
  tabItems.forEach((tabItem) => {
    tabItem.classList.remove('is-active');
  })
  tabPanelItems.forEach((tabPanelItem) => {
    tabPanelItem.classList.remove('is-show');
  })

  // クリックされたmenu要素にis-activeクラスを付加
  e.currentTarget.classList.add('is-active');
  // クリックしたmenuのデータ属性と等しい値を持つパネルにis-showクラスを付加
  tabPanelItems.forEach((tabPanelItem) => {
    if (tabPanelItem.dataset.panel ===  tabTargetData) {
      tabPanelItem.classList.add('is-show');
       console.log('Tab switch event:', tabTargetData);
      updatePaginationLinks(tabPanelItem, tabTargetData);
    }
  })

}


}
function updatePaginationLinks(activeTabPanel, activeTab) {
  const paginationLinks1 = activeTabPanel.querySelectorAll('.nagoyameshi-page-link1');
  const paginationLinks2 = activeTabPanel.querySelectorAll('.nagoyameshi-page-link2');

  paginationLinks1.forEach((link, index) => {
    const page = index + 1;
    link.href = `/reservation?page=${page}&tab=${activeTab}`;
    console.log(`Updated link1 ${index + 1}: ${link.href}`);
  });

  paginationLinks2.forEach((link, index) => {
    const page = index + 1;
    link.href = `/reservation?page=${page}&tab=${activeTab}`;
    console.log(`Updated link2 ${index + 1}: ${link.href}`);
  });
}

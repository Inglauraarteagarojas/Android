
const loader = document.getElementById('pageLoader');
const internalLinks = document.querySelectorAll('a[data-loader="true"]');
internalLinks.forEach(link => {
  link.addEventListener('click', event => {
    const href = link.getAttribute('href');
    if (!href || href.startsWith('#') || link.target === '_blank') return;
    event.preventDefault();
    loader?.classList.add('show');
    setTimeout(() => { window.location.href = href; }, 1050);
  });
});
window.addEventListener('pageshow', () => loader?.classList.remove('show'));

const menuButton = document.querySelector('.menu-btn');
const nav = document.querySelector('.nav');
menuButton?.addEventListener('click', () => nav?.classList.toggle('open'));

document.querySelectorAll('.copy-btn').forEach(btn => {
  btn.addEventListener('click', async () => {
    const code = btn.closest('.code-wrap')?.querySelector('code')?.innerText || '';
    try {
      await navigator.clipboard.writeText(code);
      const old = btn.textContent;
      btn.textContent = 'Copiado';
      setTimeout(() => btn.textContent = old, 1200);
    } catch {
      btn.textContent = 'Selecciona y copia';
    }
  });
});

const observer = new IntersectionObserver(entries => {
  entries.forEach(entry => {
    if (entry.isIntersecting) entry.target.classList.add('visible');
  });
},{threshold:.08});
document.querySelectorAll('.reveal').forEach(el => observer.observe(el));
